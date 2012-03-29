import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Lezione20 extends JPanel implements MouseListener
{
	
	protected int numeroCerchi;
	
	public Lezione20(int numeroCerchi)
	{
		
		this.numeroCerchi = numeroCerchi;
		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(400,400));
		
		this.addMouseListener(this);
		
	}
	
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){
		
		double clickX = me.getX();
		double clickY = me.getY();
		
		double distanzaDalCentro = Math.sqrt(
		
			Math.pow(clickX - this.getWidth() * 0.5, 2) +
			Math.pow(clickY - this.getHeight() * 0.5, 2)
		
		);

		int punteggio = (int)(distanzaDalCentro / (this.getWidth() * 0.5) * 10);
		
		System.out.println(Math.max(0, this.numeroCerchi - punteggio) * 10);
		
	}
	
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	protected void disegnaBersaglio(Graphics2D g2)
	{
		
		Ellipse2D.Double cerchio;

		double distanzaX = getWidth() / this.numeroCerchi;
		double distanzaY = getHeight() / this.numeroCerchi;
		
		Color colore;

		for(int i =0; i < this.numeroCerchi; i++)
		{

			cerchio = new Ellipse2D.Double(
				i * distanzaX /2,
				i * distanzaY /2,
				distanzaX * (this.numeroCerchi - i),
				distanzaY * (this.numeroCerchi - i)
			);

			colore = i%2 == 0 ? Color.BLACK : Color.WHITE;
				
			if(i == this.numeroCerchi - 1)
				colore = Color.RED;		

			g2.setColor(colore);
		
			g2.fill(cerchio);	
			
			// disegno valore punteggio cerchio
			g2.setColor(
				i%2 == 1 ? Color.BLACK : Color.WHITE
			);
			
			g2.drawString(
				(i + 1) * 10 + "",
				(int) (this.getWidth() *0.5) - 10,
				(int) (distanzaY * i * 0.5) + 20
			);			
			
		}
		
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		this.disegnaBersaglio(g2);
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("Bersaglio");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Lezione20(10));
		f.pack();
		f.setVisible(true);
		
	}
		
}