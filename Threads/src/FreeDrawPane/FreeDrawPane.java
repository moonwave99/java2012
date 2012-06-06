import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class FreeDrawPane extends JPanel implements Runnable
{

	protected boolean didInit;
	protected Thread th;
	protected int refreshTime;

	protected ArrayList<Tratto> tratti;
	protected Tratto currentTratto;
	protected boolean drawBoundingBoxes;

	protected DrawListener drawListener;

	public FreeDrawPane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

		refreshTime	= 20;

		drawListener = new DrawListener(this);
		addMouseListener(drawListener);
		addMouseMotionListener(drawListener);

		JFrame f = new JFrame("Free Draw Test");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		f.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent ke)
			{

				switch(ke.getKeyCode()){

					case KeyEvent.VK_B:

						drawBoundingBoxes = !drawBoundingBoxes;

						break;

					case KeyEvent.VK_C:

						tratti.clear();

						break;

					case KeyEvent.VK_R:

						removeMouseListener(drawListener);
						removeMouseMotionListener(drawListener);

						break;

				}

			}

		});

		f.setVisible(true);

	}

	public void setCurrentTratto(Tratto t){ this.currentTratto = t; }
	public Tratto getCurrentTratto(){ return this.currentTratto; }
	public ArrayList getTratti(){ return this.tratti; }

	protected void init()
	{

		tratti = new ArrayList<Tratto>();

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

			repaint();

			try{

				Thread.sleep(refreshTime);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	protected void drawTratti(Graphics2D g2)
	{

		if(currentTratto != null)
			currentTratto.draw(g2);

		for(int i = 0; i < tratti.size(); i++)
		{

			tratti.get(i).draw(g2);

			if(drawBoundingBoxes){

				g2.setStroke(new BasicStroke(1));
				g2.setColor(new Color(0,0,0,100));
				g2.draw(tratti.get(i).getBoundingBox());

			}

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

		drawTratti(g2);

	}

	public static void main(String[] args)
	{

		new FreeDrawPane();

	}

}