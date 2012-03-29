import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class CerchiPane extends JPanel implements MouseListener, MouseMotionListener
{
	
	protected MyEllipse elli;	// Istanza della classe MyEllipse che andremo a disegnare su schermo
	protected double initSize;	// Dimensione del cerchio da disegnare
	
	protected boolean dragging = false;	// Controllo se sto draggando
	
	public CerchiPane()
	{
		
		this.setBackground(Color.WHITE);					// Sfondo bianco
		this.setPreferredSize(new Dimension(600, 600));		// Dim preferenziale: 600 * 600
		
		this.initSize = 100;								// Assegno la dim iniziale del cerchio
		
		this.addMouseListener(this);						// Assegno ascoltatori di eventi al pannello stesso
		this.addMouseMotionListener(this);
		
	}
		
	// Se sto draggando, sposto il cerchio e aggiorno il pannello tramite repaint
	public void mouseDragged(MouseEvent me)
	{
	
		if(dragging){
			
			elli.move(me.getPoint());
			repaint();
			
		}		
		
	}
	
	// Se clicco sul cerchio, setto il pinPoint ed inizio a draggare
	public void mousePressed(MouseEvent me)
	{
		
		if(elli.contains(me.getPoint())){
			
			elli.setPinPoint(me.getPoint());
			dragging = true;
			
		}
		
	}
	
	// Quando rilascio il mouse, smetto di draggare e tolgo il pinPoint al cerchio
	public void mouseReleased(MouseEvent me)
	{
		
		if(dragging){
			
			elli.releasePinPoint();
			dragging = false;
			
		}
	
	}
	
	// Questi metodi [di implementazione comunque necessaria] non ci servono in questo programma
	public void mouseMoved(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);					// paint() della superclasse
		Graphics2D g2 = (Graphics2D)g;	// G2 is better
		
		// Filtro Antialiasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);		
		
		// Se il cerchio non è stato inizializzato, provvedo
		if(elli == null){
			
			elli = new MyEllipse(

				(this.getWidth() - this.initSize) * 0.5, 	// con questo calcolo centro il cerchio al centro del pannello
				(this.getHeight() - this.initSize) * 0.5,
				initSize,
				initSize,
				Color.RED

			);			
			
		}
		
		elli.draw(g2);	// Disegno il cerchio su schermo
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("Cerchi");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new CerchiPane());
		f.pack();
		f.setVisible(true);
		
	}
	
}

class MyEllipse extends Ellipse2D.Double
{
	
	protected Color bgColor;		// Colore del cerchio
	
	protected double pinX, pinY;	// I parametri di trascinamento, relativi al vertice in alto a sx del rettangolo contenitore
	
	public MyEllipse(double x, double y, double w, double h, Color bgColor)
	{
		
		super(x,y,w,h);
		
		this.bgColor = bgColor;
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		g2.setColor(bgColor);	// Dico al pennello di assumere il colore del cerchio
		g2.fill(this);			// Riempio il cerchio
		
	}
	
	// Aggiorno le coordinate del cerchio in funzione del punto e dei parametri di trascinamento
	public void move(Point2D p)
	{
		
		this.x = p.getX() - this.pinX;
		this.y = p.getY() - this.pinY;
		
	}	
	
	// Setto il pinPoint, che è la distanza relativa tra vertice e punto di click
	public void setPinPoint(Point2D p)
	{
		
		this.pinX = Math.abs(this.x - p.getX());
		this.pinY = Math.abs(this.y - p.getY());
		
	}
	
	// Addio pinPoint
	public void releasePinPoint()
	{
		
		this.pinX = 0;
		this.pinY = 0;		
		
	}
	
}