import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

//	Voglio disegnare una griglia [M x N] di cerchi rossi che ricopra un pannello di dimensioni W x H.

public class Cerchi extends JPanel{
	
	// m ed n sono le dimensioni della griglia - m righe, n colonne.
	protected int m, n;
	
	// Al costruttore passo la densità dei cerchi [m, n] e le dimensioni del pannello [width, height].
	public Cerchi(int m, int n, int width, int height)
	{
	
		// Assegno m ed n:
		this.m = m;
		this.n = n;
	
		// Dico al pannello di assumere le dimensioni passate:
		this.setPreferredSize(new Dimension(width, height));
		
		// Sfondo bianco
		this.setBackground(Color.WHITE);
		
	}
	
	public void paint(Graphics g)
	{
		
		// Per prima cosa, invoco il metodo di disegno della superclasse JComponent.
		super.paint(g);
		
		// Passo da Graphics a Graphics2D perché è una classe più ricca di metodi.
		Graphics2D g2 = (Graphics2D)g;

		// Non vi curate di questa istruzione, serve a limitare l'aliasing.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);				
		
		// Disegno in rosso:
		g2.setColor(Color.RED);

		// Prendo le dimensioni del singolo cerchio - le ricalcolo ad ogni paint() perché questo metodo viene invocato ad ogni resize della finestra!
		int w = getWidth() / n;
		int h = getHeight() / m;
		
		/* 
		*	Riempio la griglia con le 2 for annidate come visto a lezione.
		*	Il cerchio i-j esimo ha per dimensioni i rapporti precedenti:
		*
		*		w = larghezzaPannello / colonne;
		*		h = altezzaPannello / righe.
		*
		*	mentre ha per vertice in alto a sx il punto P = (w * i, h * j),
		*/
		
		// Mi muovo lungo le righe
		for(int i = 0; i < m; i++)
		{

			// Mi muovo lungo le colonne
			for(int j = 0; j< n; j++)
			{
				
				// Disegno un'ellisse invocando il metodo g2.fill() di Graphics2D, e passandogli un'istanza della classe Ellipse2D.Double
				g2.fill(new Ellipse2D.Double(
					
					w * i,
					h * j,
					w,
					h 
				
				));
				
				// Per tutti i metodi e le classi, fate riferimento alla documentazione di Java.
				
			}// colonne
			
		}// righe
		
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame("Cerchi");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Cerchi(10, 10, 600, 600));
		f.pack();
		f.setVisible(true);
		
	}
	
	
}