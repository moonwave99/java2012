import java.awt.*;
import java.awt.geom.*;

public class MyEllipse extends Ellipse2D.Double
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