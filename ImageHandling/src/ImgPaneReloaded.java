import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;

public class ImgPaneReloaded extends JPanel implements FileOpener
{

	protected JFrame f;
	public static final String START_PATH = "/write/your/path/here";

	protected MediaTracker tracker;
	protected Image image;

	public ImgPaneReloaded()
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
				image.getWidth(null),
				image.getHeight(null)
			)
		);

		f.pack();

	}

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		if(image != null){

			g2.drawImage(
				image,
				0,
				0,
				null
			);

		}

	}

	public void openFile(File file)
	{

		image = Toolkit.getDefaultToolkit().createImage(

			file.getAbsolutePath()

		);

		tracker.addImage(image, 0);

		try{

			tracker.waitForAll();

		}catch(InterruptedException e){

			e.printStackTrace();

		}

		this.f.setTitle(
			String.format(
				"%s - %d x %d px",
				file.getName(),
				image.getWidth(null),
				image.getHeight(null)
			)
		);

		updateCanvas();

		repaint();

	}

	public static void main(String[] args)
	{

		System.setProperty("apple.laf.useScreenMenuBar", "true");

		new ImgPaneReloaded();

	}

}

class OpenAction extends AbstractAction
{

	protected FileOpener opener;
	protected JFileChooser chooser;

	public OpenAction(FileOpener opener)
	{

		this.opener = opener;
		chooser = new JFileChooser(ImgPaneReloaded.START_PATH);

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