import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class ShapePane extends JPanel implements MouseListener, MouseMotionListener
{

	// Disegno un oggetto di tipo DraggableShape, cioè una *qualsiasi* classe che implementi tale interfaccia
	protected DraggableShape shape;

	// Mi segno se sto draggando o meno
	protected boolean dragging = false;

	public ShapePane()
	{
	
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
	
		addMouseListener(this);
		addMouseMotionListener(this);
	
	}
	
	// Iinizializzo la forma
	protected void init()
	{
	
		// Creo un oggetto di tipo ColoredCircle, che implementa DraggableShape - in dettaglio, è la sua superclasse Circle a implementarla
		shape = new ColoredCircle(
			getWidth()  * 0.5 - 50,
			getHeight() * 0.5 - 50,
			50,
			Color.RED
		);
		
	}
	
	// Dettaglio - se passo sulla forma, cambio il cursore del mouse
	public void mouseMoved(MouseEvent me)
	{
		
		setCursor(
			new Cursor(
				shape.contains(me.getPoint()) ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR
			)
		);
		
	}
	
	// Se ho cliccato sulla forma in precedenza, ne aggiorno le coordinate e ridisegno il pannello
	public void mouseDragged(MouseEvent me)
	{
	
		if(dragging){
			
			shape.move(me.getPoint());
			repaint();
			
		}
		
	}
	
	// Se il punto in cui ho cliccato è nella forma, ne aggiorno il punto di ancoraggio ed inizio a trascinare
	public void mousePressed(MouseEvent me)
	{
		
		if(shape.contains(me.getPoint())){
			
			shape.setPinPoint(me.getPoint());
			dragging = true;
			
		}		
		
	}
	
	// Quando rilascio il mouse, resetto il punto di ancoraggio della forma e smetto di draggare
	public void mouseReleased(MouseEvent me)
	{
		
		shape.releasePinPoint();
		dragging = false;
		
	}
	
	// Questi metodi non ci servono
	public void mouseClicked(MouseEvent me){}	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	public void paint(Graphics g)
	{
	
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);		
		
		if(shape == null) init();
		
		shape.draw(g2);
	
	}
	
	public static void main(String[] args)
	{
	
		JFrame f = new JFrame("Draggable Shapes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new ShapePane());
		f.pack();
		f.setVisible(true);
	
	}

}

// La classe Circle implementa DraggableShape e ne deve avere tutti i metodi: contains() non è stato ridefinito perché Ellipse2D ne è già in possesso ovviamente.
class Circle extends Ellipse2D.Double implements DraggableShape
{
	
	protected double pinX, pinY;
	
	public Circle(double x, double y, double radius)
	{
		
		super(x, y, 2*radius, 2*radius);
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		g2.draw(this);
		
	}
	
	public void move(Point p)
	{
		
		this.x = p.x - this.pinX;
		this.y = p.y - this.pinY;
		
	}
	
	public void setPinPoint(Point p)
	{
		
		this.pinX = Math.abs(this.x - p.x);
		this.pinY = Math.abs(this.y - p.y);
		
	}
	
	public void releasePinPoint()
	{
		
		this.pinX = 0;
		this.pinY = 0;
		
	}
	
}

// ColoredCircle estende Circle e pertanto implementa DraggableShape - ne ridefiniamo il draw() per dare colore alla forma
class ColoredCircle extends Circle
{
	
	protected Color bgColor;
	
	public ColoredCircle(double x, double y, double radius, Color bgColor)
	{
		
		super(x,y,radius);
		this.bgColor = bgColor;
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		g2.setColor(this.bgColor);
		g2.fill(this);
		g2.setColor(new Color(1,1,1,0.5f));
		g2.fill(new Ellipse2D.Double(x+width-30,y+30,10,10));
		
	}
	
}

// Con poco sforzo, possiamo fare la classe Square [e SquareColored se avete tempo da perdere]
class Square extends Rectangle2D.Double implements DraggableShape
{
	
	protected double pinX, pinY;
	
	public Square(double x, double y, double radius)
	{
		
		super(x, y, 2*radius, 2*radius);
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		g2.draw(this);
		
	}
	
	public void move(Point p)
	{
		
		this.x = p.x - this.pinX;
		this.y = p.y - this.pinY;
		
	}
	
	public void setPinPoint(Point p)
	{
		
		this.pinX = Math.abs(this.x - p.x);
		this.pinY = Math.abs(this.y - p.y);
		
	}
	
	public void releasePinPoint()
	{
		
		this.pinX = 0;
		this.pinY = 0;
		
	}
	
}

// L'interfaccia DraggableShape prevede metodi di base per definire una forma draggabile - non ci dà suggerimenti sull'implementazione ma pone solo delle regole da rispettare.
interface DraggableShape
{
	
	public boolean contains(Point2D p);
	public void draw(Graphics2D g2);
	public void move(Point p);
	public void setPinPoint(Point p);
	public void releasePinPoint();
	
}