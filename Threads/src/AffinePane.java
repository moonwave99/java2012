import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class AffinePane extends JPanel implements Runnable
{

	protected boolean didInit;				// controllo sul metodo init;
	protected Thread th;					// istanza della classe Thread;
	protected int refreshTime;				// tempo di refresh [in millisecondi];

	private AffineTransform rotateTransform;

	protected Rectangle2D.Double square;

	protected double time;

	public AffinePane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

		// assegno il tempo di refresh;
		refreshTime	= 20;

		// costruisco il frame *dentro* il pannello stavolta, per gestire più rapidamente gli eventi da tastiera
		JFrame f = new JFrame("Affine Transform Test");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		// KeyAdapter joins the party
		f.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent ke)
			{

				switch(ke.getKeyCode()){

					default:

						break;

				}

				repaint();

			}

		});

		f.setVisible(true);

	}

	protected void init()
	{

		rotateTransform = new AffineTransform();

		square = new Rectangle2D.Double(
			getWidth() * 0.5 - 50,
			getHeight() * 0.5 - 50,
			100,
			100
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

			time += 0.1;

			rotateTransform.rotate(
				Math.PI/180 * 2,
				square.getCenterX(),
				square.getCenterY()
			);

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
			start();

		}

		g2.setTransform(rotateTransform);
		g2.fill(square);

	}

	public static void main(String[] args)
	{

		new AffinePane();

	}

}