import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;

public class InertiaPane extends JPanel implements Runnable
{

	protected boolean didInit;
	protected Thread th;
	protected int refreshTime;

	protected Sprite sprite;
	protected Enemy enemy;

	protected ArrayList<Shot> shots;
	protected ArrayList<Ellipse2D.Double> stars;

	public static final int DENSITY = 1000;

	public InertiaPane()
	{

		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 600));

		refreshTime = 40;

		JFrame f = new JFrame("Inertia Test");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		f.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent ke)
			{

				switch(ke.getKeyCode())
				{

					case KeyEvent.VK_UP:

						sprite.vy -= 0.5;

						break;

					case KeyEvent.VK_DOWN:

						sprite.vy += 0.5;

						break;

					case KeyEvent.VK_LEFT:

						sprite.vx -= 0.5;

						break;

					case KeyEvent.VK_RIGHT:

						sprite.vx += 0.5;

						break;


					case KeyEvent.VK_SPACE:

						fireAction();

						repaint();

						break;


				}

			}

		});

		f.setVisible(true);

	}

	protected void init()
	{

		sprite = new Sprite(
			new Point2D.Double(getWidth() * 0.5, getHeight() * 0.8),
			50,
			25
		);

		enemy = new Enemy(

			new Point2D.Double(getWidth() * 0.5, getHeight() * 0.2),
			80

		);

		stars = new ArrayList<Ellipse2D.Double>();

		double radius = 0;

		for(int i = 0; i < DENSITY; i ++)
		{

			radius = Math.random() * 1;

			stars.add(new Ellipse2D.Double(

				Math.random() * getWidth(),
				Math.random() * getHeight(),
				2 * radius,
				2 * radius

			));

		}

		shots = new ArrayList<Shot>();

		didInit = true;

	}

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

			enemy.grow(0.5);

			sprite.x += sprite.vx * 2;
			sprite.y += sprite.vy * 2;

			sprite.vx -= 0.07 * Math.signum(sprite.vx);
			sprite.vy -= 0.07 * Math.signum(sprite.vy);

			for(int i = 0; i < shots.size(); i++)
			{

				shots.get(i).y -= 7;

				double distance = Math.sqrt(
					Math.pow(shots.get(i).getCenterX() - enemy.getCenterX(), 2) +
					Math.pow(shots.get(i).getCenterY() - enemy.getCenterY(), 2)
				);

				if(distance <= shots.get(i).width *0.5 + enemy.width * 0.5){

					shots.remove(i);
					enemy.shrink(10);

				}else if(shots.get(i).y <= 0){

					shots.remove(i);

				}

			}

			for(int i = 0; i < stars.size(); i++)
			{

				stars.get(i).x -= stars.get(i).getWidth() * 1.5;

				if(stars.get(i).x <= 0)
					stars.get(i).x = getWidth();

			}

			repaint();

			try{

				Thread.sleep(refreshTime);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	protected void fireAction()
	{

		shots.add(new Shot(
			new Point2D.Double(
				sprite.x + sprite.width * 0.5,
				sprite.y + sprite.height * 0.5
			),
			5
		));

	}

	protected void drawShots(Graphics2D g2)
	{

		for(int i = 0; i < shots.size(); i++)
		{

			shots.get(i).draw(g2);

		}

	}

	protected void drawStars(Graphics2D g2)
	{

		for(int i = 0; i < stars.size(); i++)
		{

			g2.setColor(new Color(1,1,1,(float)(stars.get(i).width * 0.5)));

			g2.fill(stars.get(i));

		}

	}

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

		drawStars(g2);
		sprite.draw(g2);
		enemy.draw(g2);
		drawShots(g2);

	}

	public static void main(String[] args)
	{

		new InertiaPane();

	}

}