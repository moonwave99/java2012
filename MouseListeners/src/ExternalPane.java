import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class ExternalPane extends JPanel{

	protected MyEllipse elli;
	protected boolean dragging;

	public ExternalPane()
	{
	
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
		
		OuterListener outer = new OuterListener(this);
		
		addMouseListener(outer);
		addMouseMotionListener(outer);
	
	}
	
	public MyEllipse getElli(){ return elli; }
	public boolean isDragging(){ return dragging; }
	public void setDragging(boolean dragging){ this.dragging = dragging; }
	
	public void paint(Graphics g)
	{
	
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);		
		
		if(elli == null)
			elli = new MyEllipse(getWidth()*0.5 - 50, getHeight()*0.5 - 50, 100, 100, Color.RED);
			
		elli.draw(g2);
	
	}
	
	public static void main(String[] args)
	{
	
		JFrame f = new JFrame("Prova ascoltatore esterno");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new ExternalPane());
		f.pack();
		f.setVisible(true);
		
	}

}

class OuterListener implements MouseListener, MouseMotionListener
{
	
	protected ExternalPane pane;
	
	public OuterListener(ExternalPane pane)
	{
		
		this.pane = pane;
		
	}
	
	public void mousePressed(MouseEvent me)
	{
		
		if(pane.getElli().contains(me.getPoint()))
		{
			
			pane.getElli().setPinPoint(me.getPoint());
			pane.setDragging(true);
			
		}		
		
	}
	
	public void mouseClicked(MouseEvent me){}
	public void mouseReleased(MouseEvent me)
	{

		if(pane.isDragging())
			pane.getElli().releasePinPoint();
	
		
	}
	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseMoved(MouseEvent me){}
	public void mouseDragged(MouseEvent me)
	{
		
		if(pane.isDragging()){
			
			pane.getElli().move(me.getPoint());
			pane.repaint();
			
		}		
		
	}	
	
}