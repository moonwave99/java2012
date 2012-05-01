import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class GravityPane extends JPanel implements Runnable, MouseListener, MouseMotionListener
{

	protected boolean didInit;				// controllo sul metodo init;
	protected Thread th;					// istanza della classe Thread;
	protected int refreshTime;				// tempo di refresh [in millisecondi];

	protected Rectangle2D.Double ground;	// rettangolo per h0;
	protected ArrayList<Circle> circles;	// lista di cerchi;
	protected int selectedCircle = -1;		// indice relativo al cerchio selezionato;

	public GravityPane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
		addMouseListener(this);
		addMouseMotionListener(this);

		// assegno il tempo di refresh;
		refreshTime	= 20;

		// costruisco il frame *dentro* il pannello stavolta, per gestire più rapidamente gli eventi da tastiera
		JFrame f = new JFrame("Gravity Test");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();

		// KeyAdapter joins the party
		f.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent ke)
			{

				switch(ke.getKeyCode()){

					// con la C cancello tutto
					case KeyEvent.VK_C:

						circles.clear();

						break;

					// con Spazio parto / mi fermo
					case KeyEvent.VK_SPACE:

						if(isRunning()) stop(); else start();

						break;

				}

				repaint();

			}

		});

		f.setVisible(true);

	}

	protected void init()
	{

		circles = new ArrayList<Circle>();

		ground = new Rectangle2D.Double(0, getHeight() * 0.95, getWidth(), getHeight() * 0.05);

		didInit = true;

	}

	// ************** Interface MouseListener, MouseMotionListener **************

	public void mouseDragged(MouseEvent me)
	{

		if(this.selectedCircle != -1){

			// Azzero la velocità del cerchio selezionato mentre lo trascino
			circles.get(selectedCircle).vx = 0;
			circles.get(selectedCircle).vy = 0;

			circles.get(selectedCircle).move(me.getPoint());

			// Se il Thread è fermo, faccio repaint "a mano"
			if(!isRunning())
				repaint();

		}

	}

	public void mousePressed(MouseEvent me)
	{

		this.selectedCircle = this.getSelectedCircle(me.getPoint());

		if(this.selectedCircle != -1){

			circles.get(selectedCircle).setPinPoint(me.getPoint());

		}else{

			this.createCircleOnPoint(me.getPoint());

			// Se il Thread è fermo, faccio repaint "a mano"
			if(!isRunning())
				repaint();

		}

	}

	public void mouseReleased(MouseEvent me)
	{

		if(this.selectedCircle != -1){

			circles.get(selectedCircle).releasePinPoint();
			selectedCircle = -1;

		}

	}

	public void mouseClicked(MouseEvent me)
	{

		if(this.selectedCircle != -1 && me.getClickCount() > 1)
		{

			circles.remove(selectedCircle);

		}

	}

	public void mouseMoved(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

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

			for(int i = 0; i < circles.size(); i++)
			{

				// Se ho toccato terra, assumo che la forza di gravità sia bilanciata dalla reazione vincolare del terreno
				if(circles.get(i).y + circles.get(i).height >= ground.y || i == selectedCircle)
					continue;

				// Incremento la velocità del corpo i-esimo, e la relativa posizione
				circles.get(i).vy += 0.1;
				circles.get(i).y += circles.get(i).vy;

			}

			repaint();

			try{

				Thread.sleep(refreshTime);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	// ************** Metodi protetti: selezione cerchio, creazione cerchio, metodi di disegno **************

	protected int getSelectedCircle(Point p)
	{

		for(int i = 0; i < circles.size(); i++)
		{

			if(circles.get(i).contains(p)){

				return i;

			}

		}

		return -1;

	}

	// creo un cerchio nel punto p, di raggio casuale tra 10 e 100
	protected void createCircleOnPoint(Point p)
	{

		circles.add(new Circle(p, Math.random()*90 + 10));

	}

	protected void drawCircles(Graphics2D g2)
	{

		for(int i = 0; i < circles.size(); i++)
		{

			circles.get(i).draw(g2);

		}

	}

	protected void drawGround(Graphics2D g2)
	{

		ground.setFrame(0, getHeight() * 0.95, getWidth(), getHeight() * 0.05);
		g2.setColor(new Color(70, 40, 20));
		g2.fill(ground);

	}

	protected void drawPauseString(Graphics2D g2)
	{

		g2.setColor(new Color(0,0,0,100));
		g2.setFont(new Font("Courier New",Font.BOLD,50));
		g2.drawString("Paused", (int) (getWidth() * 0.5 - 80), (int) (getHeight() *0.5 - 25));

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

		drawCircles(g2);
		drawGround(g2);

		if(!isRunning()){

			drawPauseString(g2);

		}

	}

	public static void main(String[] args)
	{

		new GravityPane();

	}

}