import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class MousePane extends JPanel implements MouseListener, MouseMotionListener
{
	
	protected Rectangle2D.Float rect;
	
	public MousePane()
	{
		
		int w = 400;
		int h = 400;		
		
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.WHITE);

		this.addMouseMotionListener( this );
		this.addMouseListener( this );		
			
		// creo un rettangolo al centro del pannello
		rect = new Rectangle2D.Float(
			w * 0.25f,
			h * 0.25f,
			w * 0.5f,
			h * 0.5f
		);		
		
	}
	
	public void paint(Graphics g)
	{
	
		// invoco metodo della superclasse [JPanel]
		super.paint(g);
		
		// casto g a g2D
		Graphics2D g2 = (Graphics2D)g;

		// lo disegno
		g2.setColor(new Color(255,0,0,100));
		g2.fill(this.rect);
		g2.setColor(new Color(255,0,0));		
		g2.draw(this.rect);
		
	}

	protected boolean isPointInRectangle(double x, double y)
	{
		
		boolean isContainedHorizontally = 
			x > rect.x && 
			x < rect.x + rect.width;
			
		boolean isContainedVertically = 
			y > rect.y && 
			y < rect.y + rect.height;
			
		return isContainedHorizontally && isContainedVertically;
		
	}

	// Interfacce di MouseListener	
	public void mouseClicked(MouseEvent me){}
	
	public void mouseEntered(MouseEvent me){}

	public void mouseExited(MouseEvent me){}
	
	public void mousePressed(MouseEvent me){
		
		// Dati: xm,ym del mouse, x,y,w,h del rettangolo
		// f(xm, ym, x, y, w, h) -> {0,1}
		
		System.out.println(this.isPointInRectangle(me.getX(), me.getY()));
		
	}
	
	public void mouseReleased(MouseEvent me){}				
	
	// Interfacce di MouseMotionListener
	public void mouseMoved(MouseEvent me){}
	
	public void mouseDragged(MouseEvent me)
	{
		
		if(this.isPointInRectangle(me.getX(), me.getY()))
		{
			
			rect.setFrame(
				me.getX() - rect.width * 0.5,
				me.getY() - rect.height * 0.5,
				rect.width,
				rect.height
			);
			
			this.repaint();
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("Prova Eventi Mouse");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new MousePane());
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
	}
	
}