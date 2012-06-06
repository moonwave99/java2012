import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawListener implements MouseListener, MouseMotionListener
{

	protected FreeDrawPane pane;

	public DrawListener(FreeDrawPane pane)
	{

		this.pane = pane;

	}

	public void mouseDragged(MouseEvent me)
	{

		if(pane.getCurrentTratto() != null){

			pane.getCurrentTratto().addPoint(me.getPoint());

		}

	}

	public void mousePressed(MouseEvent me)
	{

		if(pane.getCurrentTratto() == null){

			pane.setCurrentTratto(new Tratto());
			pane.getCurrentTratto().addPoint(me.getPoint());

		}

	}

	public void mouseReleased(MouseEvent me)
	{

		if(pane.getCurrentTratto() != null){

			pane.getTratti().add(pane.getCurrentTratto());
			pane.setCurrentTratto(null);

		}

	}

	public void mouseClicked(MouseEvent me){}
	public void mouseMoved(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

}