import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

// SinePane implementa Runnable: ne definisce quindi il metodo run()
public class SinePane extends JPanel implements Runnable
{

	// Gli elementi grafici: orbita e cercio
	protected Ellipse2D.Double path;
	protected Circle circle;

	// Campo d'istanza contenente il Thread
	protected Thread th;

	// Ci segnamo se siamo partiti
	protected boolean didInit = false;

	// Il computo del tempo
	protected double time;

	public SinePane()
	{

		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 600));

	}

	// Avrei potuto mettere queste istruzioni nel costruttore, ma essendo funzioni della dimensione del pannello ne delego l'esecuzione al paint() [una sola volta!]
	protected void init()
	{

		double padding = 20;	// Margine dell'orbita dai bordi del pannello

		path = new Ellipse2D.Double(
			padding,
			padding,
			getWidth() - padding * 2,
			getHeight() - padding * 2

		);

		circle = new Circle(
			new Point2D.Double(
				getWidth() * 0.5,
				padding
			),
			20
		);

		// Siamo partiti
		didInit = true;

	}

	// Si parte
	public void start()
	{

		if(!isRunning()){

			th = new Thread(this);
			th.start();

		}

	}

	// Ci si ferma
	public void stop()
	{

		th = null;

	}

	// Controllo se il Thread esiste
	public boolean isRunning()
	{

		return th != null;

	}

	// Il metodo run() viene invocato dal Thread tramite il metodo start() di quest'ultimo
	public void run()
	{

		// Finché il Thread è vivo...
		while(isRunning())
		{

			//...eseguo le seguenti operazioni:

			/*
				Il cerchio si muove di moto circolare uniforme lungo l'orbita, rispetta cioè la seguente legge oraria:

				x(t) = cos(t) * raggioOrbita + larghezzaPannello / 2;
				y(t) = sin(t) * raggioOrbita + altezzaPannello / 2.

				Precisamente, si tratta di una rotazione [intorno all'origine degli assi] composta con una traslazione [di fattore dimensionePannello / 2].

			*/
			circle.setCenter(new Point2D.Double(

				Math.cos(time) * path.getWidth() * 0.5 + getWidth() * 0.5,
				Math.sin(time) * path.getHeight() * 0.5 + getHeight() * 0.5

			));

			time += 0.04;	// incremento il computo del tempo di 40 millisecondi ^^

			repaint();		// no repaint() no party()

			//...e dico di fermarsi un attimo prima di rieseguirle, precisamente per 40 ms ~ 25 Hz [25 volte al secondo]

			try{

				Thread.sleep(40);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

	protected void drawPath(Graphics2D g2)
	{

		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(new Color(255,255,255,180));
		g2.draw(path);

	}

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);

		// Se non ho inizializzato, inizializzo e faccio partire il 3ad
		if(!didInit){

			init();
			start();

		}

		drawPath(g2);
		circle.draw(g2);

	}

	public static void main(String[] args)
	{

		JFrame f = new JFrame("Moto Circolare");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new SinePane());
		f.pack();
		f.setVisible(true);

	}

}

// La solita classe cerchio, stavolta con enfasi posta sulla costruzione dal centro
class Circle extends Ellipse2D.Double
{

	public Circle(Point2D center, double radius)
	{

		super(
			center.getX() - radius,
			center.getY() - radius,
			2 * radius,
			2 * radius
		);

	}

	public double getRadius(){ return this.width * 0.5; }

	public void setCenter(Point2D center)
	{

		this.x = center.getX() - this.getRadius();
		this.y = center.getY() - this.getRadius();

	}

	public void draw(Graphics2D g2)
	{

		g2.setColor(new Color(255,0,0,200));
		g2.fill(this);

	}

}