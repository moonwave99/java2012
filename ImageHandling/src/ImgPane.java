import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;

public class ImgPane extends JPanel
{

 	protected Image img;				// istanza della classe Image, necessaria per la rappresentazione dell'immagine;
	protected MediaTracker tracker;		// il tracker che gestisce i contenuti multimediali del nostro pannello;
	protected JFrame f;					// un'istanza esplicita del frame per poterne cambiare il titolo in seguito.

	// trasformazione di rotazione
	protected AffineTransform rotateTransform;
	protected int rotationCount;

	// salvo la path iniziale di navigazione in una costante statica - mi raccomando ridefinitela!:
	public static final String START_PATH = "/Users/__myusername__/Desktop";

	public ImgPane()
	{

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(300, 300));
		rotateTransform = new AffineTransform();

		// inizializzo il MediaTracker con un riferimento al componente cui vogliamo associarlo
		tracker = new MediaTracker(this);

		f = new JFrame("Img Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// non dimenticatevi di aggiungere la barra al frame!
		f.setJMenuBar(
			createMenuBar()
		);

		f.add(this);
		f.pack();

		f.setVisible(true);

	}

	protected JMenuBar createMenuBar()
	{

		// la gerarchia dei menu: JMenuBar > JMenu > JMenuItem
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");

		// all'elemento Open associo l'azione di apertura file
		openItem.addActionListener(new OpenAction(this));

		// a tale elemento associo uno shortcut da tastiera [CTRL+O]
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

		// aggiungo ogni elemento al proprio padre
		fileMenu.add(openItem);
		bar.add(fileMenu);

		//idem per l'altra azione
		JMenu imageMenu = new JMenu("Image");
		JMenuItem rotateItem = new JMenuItem("Rotate");

		rotateItem.addActionListener(new RotateAction(this));
		rotateItem.setAccelerator(
			KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK)
		);

		imageMenu.add(rotateItem);
		bar.add(imageMenu);

		return bar;

	}

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		// applico la matrice associata alla trasformazione al contesto grafico [di default è l'identità!]
		g2.setTransform(rotateTransform);

		// disegno l'immagine a partire dal punto (0,0)
		if(img != null){

			g2.drawImage(
				img,
				0,
				0,
				null
			);

		}

	}

	// adatto il frame all'immagine
	protected void updateCanvas()
	{

		int w = img.getWidth(null);
		int h = img.getHeight(null);

		// aggiorno le dimensioni del pannello in funzione della dimensione dell'immagine
		setPreferredSize( new Dimension(w, h) );

		// riaggiorno il frame di conseguenza!
		f.pack();

	}

	// metodo per aprire un'immagine
	public void openImage(File imgFile)
	{

		// creo un'immagine a partire da File tramite metodo createImage(File f) di Toolkit
		img = Toolkit.getDefaultToolkit().createImage(imgFile.getAbsolutePath());

		// aggiungo l'immagine al tracker
		tracker.addImage(img, 0);

		// attendo che il tracker carichi tutti i contenuti
		try{

			tracker.waitForAll();

		}catch(InterruptedException e){

			e.printStackTrace();

		}

		rotateTransform = new AffineTransform();
		rotationCount = 0;

		// adatto il frame all'immagine
		updateCanvas();

		// aggiorno il titolo del frame
		f.setTitle(String.format("%s - %d x %d", imgFile.getName(), img.getWidth(null), img.getHeight(null) ));

		repaint();

	}

	// ruota l'immagine di 90° rispetto al centro del pannello
	public void rotate()
	{

		if(img == null)
			return;

		int w = img.getWidth(null);
		int h = img.getHeight(null);

		rotationCount++;

		rotateTransform.rotate(
			Math.PI/2,
			w * 0.5,
			h * 0.5
		);

		// adatto il frame all'immagine
		updateCanvas();

		repaint();

	}

	public static void main(String[] args)
	{

		System.setProperty("apple.laf.useScreenMenuBar","true");
		new ImgPane();

	}

}

// un'AbstractAction è una classe che incapsula un'azione richiamabile da contesti differenti [es. voci di menu, pulsanti] - implementa ovviamente ActionListener
class OpenAction extends AbstractAction
{

	// riferimento al pannello
	protected ImgPane pane;

	public OpenAction(ImgPane pane)
	{

		this.pane = pane;

	}

	public void actionPerformed(ActionEvent e)
	{

		// creo un JFileChooser per sfogliare le cartelle a partire dal percorso indicato
		JFileChooser fC = new JFileChooser(ImgPane.START_PATH);

		// se ho selezionato un file...
	    if(fC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

	    	try{

				//...provo ad aprirlo dal pannello:
				pane.openImage(fC.getSelectedFile());

	    	}catch(Exception ex){

				// altrimenti gestico l'eccezione [occhio a chiamare differentemente il riferimento ad Exception, 'e' è già occupato da ActionEvent in questo metodo!].
	    		ex.printStackTrace();
	    		JOptionPane.showMessageDialog(null, "Error opening file.");

	    	}
	    }

	}

}

// altra azione - questa ruota l'immagine visualizzata
class RotateAction extends AbstractAction
{

	protected ImgPane pane;

	public RotateAction(ImgPane pane)
	{

		this.pane = pane;

	}

	public void actionPerformed(ActionEvent e)
	{

		pane.rotate();

	}

}