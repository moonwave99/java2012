import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class LineePane extends JPanel implements MouseListener, MouseMotionListener
{
		
	protected Line2D.Double diagonale1, diagonale2;	// istanze di Line2D per le diagonali
	
	protected Quadrato q;	// istanza della nostra classe Quadrato
		
	protected boolean dragging = false;	
	
	protected boolean block = false;
		
	public LineePane()
	{
		
		this.setBackground(Color.WHITE);					// Sfondo bianco
		this.setPreferredSize(new Dimension(600, 600));		// Dim preferenziale: 600 * 600

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public void mouseMoved(MouseEvent me)
	{

		q.highlight(q.contains(me.getPoint()));
		repaint();
		
	}
	
	
	public void mouseDragged(MouseEvent me)
	{
		
		if(dragging){

			System.out.println(me.getX() + " " + me.getY());
			
			if(block){

				q.move(
					Math.min(
						Math.max(me.getX(), q.lato * 0.5),
						this.getWidth() - q.lato * 0.5
					),
					Math.min(
						Math.max(me.getY(), q.lato * 0.5),
						this.getHeight() - q.lato * 0.5
					)
				);
				
			}else{
				
				q.move(me.getPoint());				
				
			}

			repaint();
			
		}
		
	}

	public void mousePressed(MouseEvent me)
	{
		
		this.dragging = q.contains(
			me.getPoint()
		);
			
	}
	
	public void mouseClicked(MouseEvent me){
		
		if(me.getClickCount() > 1)
			block = !block;
		
	}
	
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	// Inizializzo le figure una sola volta
	protected void init()
	{
		
		diagonale1 = new Line2D.Double(
			0,
			0,
			this.getWidth(),
			this.getHeight()
		);
		
		diagonale2 = new Line2D.Double(
			this.getWidth(),
			0,
			0,
			this.getHeight()
		);
		
		q = new Quadrato(
			(this.getWidth() - 100) * 0.5,
			(this.getHeight() - 100) * 0.5,
			100
		);
		
	}
	
	// Divido i metodi di disegni in più metodi separati, in questo caso per le diagonali
	protected void disegnaDiagonali(Graphics2D g2)
	{
		
		g2.setColor(Color.RED);
		
		g2.setStroke(
			new BasicStroke(7.5f)
		);		
		
		g2.draw(diagonale1);			
		g2.draw(diagonale2);	
		
	}
	
	// Aggiorno le coordinate delle diagonali in modo che si adattino ogni volta alle dimensioni del pannello
	protected void aggiornaDiagonali()
	{
		
		diagonale1 = new Line2D.Double(0,0,getWidth(),getHeight());
		diagonale2 = new Line2D.Double(getWidth(),0,0,getHeight());
		
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);					// paint() della superclasse
		Graphics2D g2 = (Graphics2D)g;	// G2 is better

		// Filtro Antialiasing
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);
		
		
		if(diagonale1 == null)
			this.init();
		
		this.aggiornaDiagonali();
		//this.disegnaDiagonali(g2);
		
		// Disegno il nostro Quadrato
		q.draw(g2);
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("Linee");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new LineePane());
		f.pack();
		f.setVisible(true);
		
	}
	
}

// La nostra classe Quadrato, implementata con 4 Line2D
class Quadrato {
	
	public double x, y;	// coordinate del vertice in alto a sx
	
	public double lato;	// lunghezza del lato
	
	protected Line2D.Double[] lati;	// array dei lati, protetto
	
	protected float stroke;
	
	public Quadrato(double x, double y, double lato)
	{
		
		this.x = x;
		this.y = y;
		this.lato = Math.abs(lato);
		
		this.stroke = 1.0f;
		
		this.init();
		
	}
	
	// Inizializzo i 4 lati in funzione di x, y e lato
	protected void init()
	{
		
		lati = new Line2D.Double[4];
		
		lati[0] = new Line2D.Double(
			x,
			y,
			x+lato,
			y
		);
		
		lati[1] = new Line2D.Double(
			x + lato,
			y,
			x + lato,
			y + lato
		);
		
		lati[2] = new Line2D.Double(
			x,
			y + lato,
			x + lato,
			y + lato
		);	
		
		lati[3] = new Line2D.Double(
			x,
			y,
			x,
			y + lato
		);					
		
	}
	
	public void draw(Graphics2D g2)
	{
	
		g2.setStroke(new BasicStroke(this.stroke));
	
		for(int i = 0; i < lati.length; i ++)
		{
			
			g2.draw(lati[i]);
			
		}
		
	}
	
	public void move(Point p)
	{

		this.x = (p.x - this.lato / 2);
		this.y = (p.y - this.lato / 2);
		
		init();

	}
	
	public void move(double x, double y)
	{

		this.x = (x - this.lato / 2);
		this.y = (y - this.lato / 2);
		
		init();

	}	
	
	public boolean contains(Point p)
	{
		
		return (p.x >= this.x && p.y >= this.y)
			&& (p.x <= this.x + lato && p.y <= this.y + lato);
			
		
	}
	
	public void highlight(boolean on)
	{
		
		this.stroke = on ? 5.0f : 1.0f;
		
	}
	
	public String toString()
	{
		
		return "x: " + x +" y: " + y +" lato: " + lato;
		
	}
		
}