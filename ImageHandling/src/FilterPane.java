import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class FilterPane extends JPanel implements FileOpener
{

	protected JFrame f;

	// ricordatevi di cambiare questo con una path sensata! [es. /Users/vostronomeutente/Desktop]
	public static final String START_PATH = "/write/your/path/here";

	protected MediaTracker tracker;

	// ci occorrono tre istanze di BufferedImage: l'immagine originale, quella filtrata e quela visualizzata
	protected BufferedImage loadedImage, filteredImage, displayImage;

	// costanti che definiscono il tipo di filtri usati
	public final static int RESET = 0;
	public final static int BLUR = 1;
	public final static int SHARPEN = 2;

	public FilterPane()
	{

		this.setPreferredSize( new Dimension(300, 300) );
		this.setBackground(Color.GRAY);

		tracker = new MediaTracker( this );

		f = new JFrame("Image Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);

		f.setJMenuBar( this.createMenuBar() );

		f.pack();
		f.setVisible(true);

	}

	protected JMenuBar createMenuBar()
	{

		JMenuBar bar = new JMenuBar();

		// Costruisco il menu File
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new OpenAction( this ));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

		fileMenu.add(openItem);
		bar.add(fileMenu);

		// Costruisco il menu Filter
		JMenu filterMenu = new JMenu("Filters");
		JMenuItem blurItem = new JMenuItem("Blur");
		JMenuItem sharpenItem = new JMenuItem("Sharpen");
		JMenuItem resetItem = new JMenuItem("Reset");
		blurItem.addActionListener(new FilterAction(this, FilterPane.BLUR));
		sharpenItem.addActionListener(new FilterAction(this, FilterPane.SHARPEN));
		resetItem.addActionListener(new FilterAction(this, FilterPane.RESET));

		filterMenu.add(blurItem);
		filterMenu.add(sharpenItem);
		filterMenu.add(resetItem);
		bar.add(filterMenu);

		// Costruisco il menu Help
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new AboutAction());

		helpMenu.add(aboutItem);
		bar.add(helpMenu);

		return bar;

	}

	protected void updateCanvas()
	{

		this.setPreferredSize(
			new Dimension(
				loadedImage.getWidth(null),
				loadedImage.getHeight(null)
			)
		);

		f.pack();

	}

	public void openFile(File file)
	{

		// Carichiamo i dati del file all'interno di una semplice Image
		Image tempImage = Toolkit.getDefaultToolkit().createImage(

			file.getAbsolutePath()

		);

		tracker.addImage(tempImage, 0);

		try{

			tracker.waitForAll();

		}catch(InterruptedException e){

			e.printStackTrace();

		}

		this.f.setTitle(
			String.format(
				"%s - %d x %d px",
				file.getName(),
				tempImage.getWidth(null),
				tempImage.getHeight(null)
			)
		);

		// Creo una BufferedImage vuota con dimensioni pari a quella dell'Image caricata da file
	    loadedImage = new BufferedImage(
			tempImage.getWidth(this),
			tempImage.getHeight(this),
			BufferedImage.TYPE_INT_RGB
		);

		// Creo un oggetto Graphics2D per poter disegnare effettivamente all'interno della BufferedImage
    	Graphics2D bufLoad = loadedImage.createGraphics();
    	bufLoad.drawImage(tempImage, 0, 0, this);

		// Inizializzo le altre due BufferedImage
		filteredImage = new BufferedImage(
			tempImage.getWidth(this),
			tempImage.getHeight(this),
			BufferedImage.TYPE_INT_RGB
		);

		displayImage = new BufferedImage(
			tempImage.getWidth(this),
			tempImage.getHeight(this),
			BufferedImage.TYPE_INT_RGB
		);

		// Riempio la displayImage
		Graphics2D bufDisp = displayImage.createGraphics();
		bufDisp.drawImage(tempImage, 0, 0, this);

		updateCanvas();

		repaint();

	}

	public void filterImage(int filterType)
	{

		float[] filter;

		// A seconda del tipo di filtro scelto, creo il corrispondente nucleo
		switch(filterType){

			case FilterPane.BLUR:

				filter = new float[]{
					0.0625f, 0.125f, 0.0625f,
					0.125f, 0.25f, 0.125f,
					0.0625f, 0.125f, 0.0625f
				};

				break;

			case FilterPane.SHARPEN:

				filter = new float[]{
					-1.0f, -1.0f, -1.0f,
					-1.0f, 9.0f, -1.0f,
					-1.0f, -1.0f, -1.0f
				};

				break;

			default:

				filter = new float[]{
					0, 0, 0,
					0, 1, 0,
					0, 0, 0
				};

				break;

		}

		// Inizializzo il Kernel secondo il filtro scelto
		Kernel kernel = new Kernel(3, 3, filter);

		// Definisco un'operazione di convoluzione con il kernel appena creato
	    ConvolveOp convolve = new ConvolveOp(
			kernel,
			ConvolveOp.EDGE_NO_OP,
	        null
		);

		// Applico la convoluzione sull'immagine caricata, con destinazione la filteredImage
	    convolve.filter(loadedImage, filteredImage);

		// Assegno la filteredImage al campo displayImage affinché venga visualizzata
		displayImage = filteredImage;

		repaint();

	}

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		if(displayImage != null){

			g2.drawImage(
				displayImage,
				0,
				0,
				null
			);

		}

	}

	public static void main(String[] args)
	{

		System.setProperty("apple.laf.useScreenMenuBar", "true");

		new FilterPane();

	}

}

// Il comportamento delle azioni è il medesimo rispetto a quanto visto nel file precedente
class OpenAction extends AbstractAction
{

	protected FileOpener opener;
	protected JFileChooser chooser;

	public OpenAction(FileOpener opener)
	{

		this.opener = opener;
		chooser = new JFileChooser(FilterPane.START_PATH);

	}

	public void actionPerformed(ActionEvent e)
	{

		if( chooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ){

			opener.openFile(

				chooser.getSelectedFile()

			);

		}

	}

}

class FilterAction extends AbstractAction
{

	protected FilterPane pane;
	protected int filterType;

	public FilterAction(FilterPane pane, int filterType)
	{

		this.pane = pane;
		this.filterType = filterType;

	}

	public void actionPerformed(ActionEvent e)
	{

		// Viene invocato il metodo filterImage() con il parametro filterType per sceglierne il tipo
		pane.filterImage(filterType);

	}

}

class AboutAction extends AbstractAction
{

	public AboutAction()
	{

	}

	public void actionPerformed(ActionEvent e)
	{

		JOptionPane.showMessageDialog(null, "About this prog.");

	}

}

interface FileOpener{

	public void openFile(File f);

}