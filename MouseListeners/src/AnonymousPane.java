import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class AnonymousPane extends JPanel{

	protected MyEllipse elli;
	protected boolean dragging;

	public AnonymousPane()
	{
	
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
		
		addMouseListener(new MouseListener(){
		
			public void mousePressed(MouseEvent me)
			{
				
				if(elli.contains(me.getPoint()))
				{
					
					elli.setPinPoint(me.getPoint());
					dragging = true;
					
				}
				
			}	
				
			public void mouseClicked(MouseEvent me){}
			public void mouseReleased(MouseEvent me)
			{
				
				if(dragging)
					elli.releasePinPoint();
				
			}
			public void mouseEntered(MouseEvent me){}
			public void mouseExited(MouseEvent me){}
			
		});
		
		addMouseMotionListener(new MouseMotionListener(){
		
			public void mouseMoved(MouseEvent me){}
			
			public void mouseDragged(MouseEvent me)
			{
				
				if(dragging){
					
					elli.move(me.getPoint());
					repaint();
					
				}
				
			}						
			
		});
	
	}
	
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
	
		JFrame f = new JFrame("Prova ascoltatori annidati anonimi");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new AnonymousPane());
		f.pack();
		f.setVisible(true);
		
	}

}