import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class GrowPane extends JPanel implements Runnable
{

	protected boolean didInit;				// controllo sul metodo init;
	protected Thread th;					// istanza della classe Thread;
	protected int refreshTime;				// tempo di refresh [in millisecondi];

	protected Circle circle;

	protected int time;

	public GrowPane()
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

				switch(ke.getKeyCode())
				{

					case KeyEvent.VK_R:

						circle.randomizeDirection();

						break;

					case KeyEvent.VK_SPACE:

						if(isRunning())
							stop();
						else
							start();

						break;

				}

				repaint();

			}

		});

		f.setVisible(true);

	}

	protected void init()
	{

		circle =  new Circle(
			new Point2D.Double(getWidth() * 0.5, getHeight() * 0.5),
			0
		);

		circle.randomizeDirection();

		didInit = true;

	}

	// ************** Metodi relativi al Thread: start, stop, isRunning, run **************

	public void start()
	{

		if(!isRunning()){

			th = new Thread(this);
			th.start();
			System.out.println("Thread partito.");

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

			circle.width = (Math.sin(time * Math.PI / 180 - Math.PI/2) + 1) * 0.5 * getWidth();
			circle.height = (Math.sin(time * Math.PI / 180 - Math.PI/2) + 1) * 0.5 * getHeight();
			circle.x = getWidth() * 0.5 - circle.getRadius();
			circle.y = getHeight() * 0.5 - circle.getRadius();

			time ++;

			if(time % 50 == 0){

				circle.randomizeDirection();

			}

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

		new GrowPane();

	}

}