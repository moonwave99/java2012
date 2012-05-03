import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class AccelerazionePane extends JPanel implements Runnable
{

	protected boolean didInit;				// controllo sul metodo init;
	protected Thread th;					// istanza della classe Thread;
	protected int refreshTime;				// tempo di refresh [in millisecondi];

	protected Circle circle;

	public AccelerazionePane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

		// assegno il tempo di refresh;
		refreshTime	= 20;

		// costruisco il frame *dentro* il pannello stavolta, per gestire più rapidamente gli eventi da tastiera
		JFrame f = new JFrame("Accelerazione Test");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		// KeyAdapter joins the party
		f.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent ke)
			{



				repaint();

			}

		});

		f.setVisible(true);

	}

	protected void init()
	{

		circle =  new Circle(
			new Point2D.Double(getWidth() * 0.5, getHeight() * 0.5),
			50
		);

		didInit = true;

	}

	// ************** Metodi relativi al Thread: start, stop, isRunning, run **************

	public void start()
	{

		if(!isRunning()){

			th = new Thread(this);
			th.start();

		}

	}

	public void stop()
	{

		th = null;

	}

	public boolean isRunning()
	{

		return th != null;

	}

	public void run()
	{

		while(isRunning())
		{



			repaint();

			try{

				Thread.sleep(refreshTime);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	// ************** Good ol' paint + main **************

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);

		if(!didInit){

			init();

		}

		g2.setColor(Color.BLUE);
		g2.fill(circle);

	}

	public static void main(String[] args)
	{

		new AccelerazionePane();

	}

}