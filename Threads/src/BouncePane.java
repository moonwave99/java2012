import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;

public class BouncePane extends JPanel implements Runnable
{

	protected Thread th;

	final static int MAX_CIRCLES = (int)(Math.pow(2, 5));

	protected ArrayList<MyCircle> circles;

	public BouncePane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

	}

	public void start()
	{

		if(!isRunning())
		{

			th = new Thread( this );
			th.setPriority(Thread.MAX_PRIORITY);

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

			checkCollisions();

			for(int i = 0; i < circles.size(); i++)
			{

				circles.get(i).x += circles.get(i).vx * 4;
				circles.get(i).y += circles.get(i).vy * 4;

			}

			repaint();

			try{

				Thread.sleep(20);

			}catch(java.lang.InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	protected void checkCollisions()
	{

		ArrayList<MyCircle> spawn = new ArrayList<MyCircle>();

		for(int i = 0; i < circles.size(); i++)
		{

			if(circles.get(i).x <= 0 || circles.get(i).x + circles.get(i).width >= getWidth()){

				if(circles.size() >= BouncePane.MAX_CIRCLES){

					circles.get(i).vx *=-1;
					continue;

				}

				spawn.add(new MyCircle(

					new Point2D.Double(circles.get(i).getCenterX(), circles.get(i).getCenterY()),
					circles.get(i).getRadius() * 0.5,
					circles.get(i).vx * -1,
					circles.get(i).vy

				));

				spawn.add(new MyCircle(

					new Point2D.Double(circles.get(i).getCenterX(), circles.get(i).getCenterY()),
					circles.get(i).getRadius() * 0.5,
					circles.get(i).vx * -1,
					circles.get(i).vy * -1

				));

				circles.remove(i);

			}else if(circles.get(i).y <= 0 || circles.get(i).y + circles.get(i).height >= getHeight()){

				if(circles.size() >= BouncePane.MAX_CIRCLES){

					circles.get(i).vy *=-1;
					continue;

				}

				spawn.add(new MyCircle(

					new Point2D.Double(circles.get(i).getCenterX(), circles.get(i).getCenterY()),
					circles.get(i).getRadius() * 0.5,
					circles.get(i).vx,
					circles.get(i).vy * -1

				));

				spawn.add(new MyCircle(

					new Point2D.Double(circles.get(i).getCenterX(), circles.get(i).getCenterY()),
					circles.get(i).getRadius() * 0.5,
					circles.get(i).vx * -1,
					circles.get(i).vy * -1

				));

				circles.remove(i);

			}

		}

		for(int i = 0; i < spawn.size(); i++)
		{

			circles.add(spawn.get(i));

		}

	}

	protected void addCircle(double x, double y, double radius, double vx, double vy)
	{

		MyCircle c = new MyCircle(x, y, radius);
		c.vx = vx;
		c.vy = vy;

		circles.add(c);

	}

	protected void drawCircles(Graphics2D g2)
	{

		for(int i = 0; i<circles.size(); i++)
		{

			g2.fill(circles.get(i));

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

		if(circles == null){

			circles = new ArrayList<MyCircle>();

			addCircle(
				getWidth()*0.5 - 128,
				getHeight()*0.5 - 128,
				128,
				Math.random()*2 -1,
				Math.random()*2 -1
			);

			start();

		}

		g2.setColor(new Color(255, 0, 0, 128));
		drawCircles(g2);

	}

	public static void main(String[] args)
	{

		JFrame f = new JFrame("Prova Thread");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new BouncePane());
		f.pack();
		f.setVisible(true);

	}

}

class MyCircle extends Ellipse2D.Double
{

	public double vx;
	public double vy;

	public MyCircle(double x, double y, double radius)
	{

		super(x, y, 2*radius, 2*radius);

	}

	public MyCircle(Point2D center, double radius, double vx, double vy)
	{

		super(center.getX() - radius, center.getY() - radius, 2*radius, 2*radius);

		this.vx = vx;
		this.vy = vy;

	}

	public double getRadius(){ return this.width * 0.5; }

}