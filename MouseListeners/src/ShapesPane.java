import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import java.util.ArrayList;

public class ShapesPane extends JPanel implements MouseListener, MouseMotionListener
{

	protected ArrayList<DraggableShape> shapes;

	protected int selectedShape;
	
	protected Color bgColor;	

	public ShapesPane()
	{
	
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
	
		addMouseListener(this);
		addMouseMotionListener(this);
		
		// shapes è una lista di elementi DraggableShape, cioè che implementino tale interfaccia [a prescindere dal loro tipo!]
		this.shapes = new ArrayList<DraggableShape>();
		
		// per -1 si indica 'nessun elemento selezionato', visto che l'indice degli elementi della lista parte da 0
		selectedShape = -1;
		
		// Colore dei cerchi di default - rosso con opacità 100 [su 255];
		this.bgColor = new Color(255,0,0,100);
	
	}


	public void mouseMoved(MouseEvent me)
	{

		// Aggiorno la selezione in funzione delle coordinate del mouse
		this.selectedShape = this.getSelectedShape(me.getPoint());
		
		// Illumino l'elemento selezionato
		highlight(this.selectedShape);
		
		// No repaint(), no modifiche su schermo - occhio!
		repaint();
		
	}
	
	public void mouseDragged(MouseEvent me)
	{
	
		// Se ho selezionato qualcosa...
		if(this.selectedShape != -1){
	
			//...ne aggiorno le coordinate in funzione del mouse
			shapes.get(selectedShape).move(me.getPoint());
			repaint();
			
		}
		
	}

	public void mousePressed(MouseEvent me)
	{
		
		// Se ho selezionato qualcosa...
		if(this.selectedShape != -1){
			
			//...ne indico il punto di trascinamento
			shapes.get(selectedShape).setPinPoint(me.getPoint());	
			
		}else{

			// Altrimenti creo una forma nel punto di clic
			this.createShapeOnPoint(me.getPoint());
			
			// Mi raccomando il repaint ogni volta che aggiungete qualcosa!		
			repaint();			
			
		}

	}	

	public void mouseReleased(MouseEvent me)
	{

		// Se ho selezionato qualcosa...
		if(this.selectedShape != -1){
			
			//...ne rilascio il punto di ancoraggio
			shapes.get(selectedShape).releasePinPoint();
			
		}
		
	}
	
	public void mouseClicked(MouseEvent me)
	{
		
		// Se ho selezionato qualcosa ed ho fatto doppio click...
		if(this.selectedShape != -1 && me.getClickCount() > 1)
		{
			
			//...rimuovo l'elemento selezionato dalla lista
			shapes.remove(selectedShape);
			repaint();
			
		}
		
	}
		
	// Ospiti non graditi
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	// Evidenzio esclusivamente l'elemento selezionato
	protected void highlight(int selected)
	{
		
		for(int i = 0; i < shapes.size(); i++)
		{
			
			shapes.get(i).highlight( i == selected );
			
		}
		
	}
	
	// Seleziono una forma in funzione del punto di clic - restituisco l'indice all'interno della lista, oppure -1 se nulla è stato colpito
	protected int getSelectedShape(Point p)
	{

		for(int i = 0; i < shapes.size(); i++)
		{
			
			if(shapes.get(i).contains(p)){
				
				return i;
				
			}
			
		}

		return -1;
		
	}
	
	// Creo un nuovo cerchio, centrato nel punto passato come parametro
	protected void createShapeOnPoint(Point p)
	{
		
		shapes.add(new ColoredCircle(
			p.x - 50,
			p.y - 50,
			50,			
			bgColor
		));
		
	}
	
	// Disegno tutte le forme contenute nell'ArrayList
	protected void drawShapes(Graphics2D g2)
	{
		
		for(int i = 0; i < shapes.size(); i++)
		{
			
			shapes.get(i).draw(g2);
			
		}
		
	}
	
	public void paint(Graphics g)
	{
	
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);		

		this.drawShapes(g2);
	
	}
	
	public static void main(String[] args)
	{
	
		JFrame f = new JFrame("Draggable Shapes");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new ShapesPane());
		f.pack();
		f.setVisible(true);
	
	}

}

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
	
	// DraggableShape ha 1 nuovo metodo da definire - lo definiamo qui, senza implementarlo
	public void highlight(boolean on){}	
	
}

// ColoredCircle estende Circle e pertanto implementa DraggableShape - ne ridefiniamo il draw() per dare colore alla forma
class ColoredCircle extends Circle
{
	
	protected Color bgColor;
	protected boolean selected = false;	
	
	public ColoredCircle(double x, double y, double radius, Color bgColor)
	{
		
		super(x,y,radius);
		this.bgColor = bgColor;
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		// Se selezionato, disegno il cerchio totalmente opaco
		g2.setColor( selected ? new Color(
		
			this.bgColor.getRed(),
			this.bgColor.getGreen(),
			this.bgColor.getBlue(),
			255
			
		) : this.bgColor);
		
		g2.fill(this);
		
	}	
	
	// Definiamo il metodo highlight()
	public void highlight(boolean on)
	{
		
		this.selected = on;
		
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
	
	// Abbiamo un nuovo amico metodo
	public void highlight(boolean on);
	
}