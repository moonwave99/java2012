import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class DragListener implements MouseListener, MouseMotionListener
{

	protected NewDrawPane pane;

	protected Tratto dragTratto;

	public DragListener(NewDrawPane pane)
	{

		this.pane = pane;

	}

	public void mouseDragged(MouseEvent me)
	{

		if(dragTratto != null){

			dragTratto.translate(me.getPoint());

		}

	}

	public void mousePressed(MouseEvent me)
	{

		for(int i = 0; i < pane.getTratti().size(); i++)
		{

			if(pane.getTratti().get(i).getBoundingBox().contains(me.getPoint()))
			{

				dragTratto = pane.getTratti().get(i);
				break;

			}

		}

	}

	public void mouseReleased(MouseEvent me)
	{



	}

	public void mouseClicked(MouseEvent me){}
	public void mouseMoved(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

}