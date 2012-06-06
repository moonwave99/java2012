import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class NewDrawPane extends JPanel implements Runnable
{

	protected boolean didInit;
	protected Thread th;
	protected int refreshTime;

	protected ArrayList<Tratto> tratti;
	protected Tratto currentTratto;

	protected NewDrawListener drawListener;
	protected DragListener dragListener;

	protected boolean drawBoxes;

	public NewDrawPane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));

		refreshTime	= 20;

		drawListener = new NewDrawListener(this);
		dragListener = new DragListener(this);

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

						drawBoxes = !drawBoxes;

						break;

					case KeyEvent.VK_1:

						removeMouseListener(dragListener);
						removeMouseMotionListener(dragListener);
						addMouseListener(drawListener);
						addMouseMotionListener(drawListener);

						break;

					case KeyEvent.VK_2:

						removeMouseListener(drawListener);
						removeMouseMotionListener(drawListener);
						addMouseListener(dragListener);
						addMouseMotionListener(dragListener);

						break;

				}

			}

		});

		f.setVisible(true);

	}

	public void setCurrentTratto(Tratto t){this.currentTratto = t;}
	public Tratto getCurrentTratto(){return currentTratto; }
	public ArrayList<Tratto> getTratti(){ return this.tratti; }

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

			if(drawBoxes){

				g2.setStroke(new BasicStroke(0.5f));
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

		new NewDrawPane();

	}

}