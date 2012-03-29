import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Lezione21 extends JPanel implements MouseListener, MouseMotionListener{
	
	protected MyCircle circle;
	protected int circleSize;
	
	public Lezione21()
	{
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(600,600));
		
		circleSize = 50;
		
		circle = new MyCircle(
			new Point2D.Double(300, 300),
			circleSize
		);		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		circle.draw(g2);
		
	}
	
	public void mouseMoved(MouseEvent me)
	{
		
		circle.highlight(
			circle.isPointContained(
				me.getPoint()
			)
		);
		
		repaint();
		
	}
	
	public void mouseDragged(MouseEvent me)
	{
		
		if(circle.isPointContained(me.getPoint()))
		{
			
			circle.setPosition(me.getPoint());
			
			repaint();
			
		}
		
	}
	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		
		if(circle.isPointContained(me.getPoint()))
		{
			
			circle.setPinPoint(me.getPoint());
			
		}
		
	}
	public void mouseReleased(MouseEvent me)
	{
		
		circle.clearPinPoint();
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Lezione21());
		f.pack();
		f.setVisible(true);
		
	}
	
}

class MyCircle extends Ellipse2D.Double
{
	
	protected Color color;
	protected Color borderColor = Color.BLACK;
	public Point2D center;
	
	public Point2D pinPoint;
	protected double pinX, pinY;
	
	public MyCircle(double x, double y, double width, double height)
	{
		
		super(x, y, width, height);
		this.setColor(Color.RED);
		
		this.center = new Point2D.Double(
			x + width * 0.5,
			y + height * 0.5
		);
		
	}
	
	public MyCircle(double x, double y, double width, double height, Color color)
	{
		
		super(x, y, width, height);
		this.setColor(color);
		
		this.center = new Point2D.Double(
			x + width * 0.5,
			y + height * 0.5
		);		
		
	}	
	
	public MyCircle(Point2D center, double radius)
	{
		
		super(
			center.getX() - radius,
			center.getY() - radius,
			2 * radius,
			2 * radius
		);
		
		this.setColor(Color.RED);	
		
		this.center = center;
		
	}	
	
	public void clearPinPoint()
	{
		
		this.pinPoint = null;
		this.pinX = 0;
		this.pinY = 0;
		
	}
	
	public void setPinPoint(Point2D p){
		
		this.pinPoint = p;
		
		this.pinX = Math.abs(this.x - p.getX());
		this.pinY = Math.abs(this.y - p.getY());			

	}
	
	public void setColor(Color color){ this.color = color; }
	public Color getColor(){ return this.color; }
	
	public void highlight(boolean on)
	{
		
		this.borderColor = on ? Color.GREEN : Color.BLACK;
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		g2.setColor(this.getColor());
		g2.fill(this);
		g2.setColor(this.borderColor);
		g2.draw(this);
		
	}
	
	public void setPosition(Point2D p)
	{
		
		this.x = p.getX() - this.pinX;		
		this.y = p.getY() - this.pinY;
		
		this.center = new Point2D.Double(
		
			this.x + this.width * 0.5,
			this.y + this.height * 0.5			
		
		);
		
	}
	
	public void setPositionFromCenter(Point2D p)
	{

		this.x = p.getX() - this.width * 0.5;
		this.y = p.getY() - this.height * 0.5; 		
		
		this.center = p;		
		
	}
	
	public boolean isPointContained(Point2D p)
	{
		
		return Math.sqrt(
			Math.pow(p.getX() - this.center.getX(), 2) + 
			Math.pow(p.getY() - this.center.getY(), 2)			
		) <= this.width * 0.5;
		
	}
	
}