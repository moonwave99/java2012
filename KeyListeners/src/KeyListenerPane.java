import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class KeyListenerPane extends JPanel
{
	
	protected ArrayList<Ellipse2D.Double> shapes;
	protected Color bgColor;
	
	public KeyListenerPane()
	{
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600,600));
		
		this.shapes = new ArrayList<Ellipse2D.Double>();
		this.bgColor = Color.RED;
		
		// Stavolta costruisco il frame nel pannello stesso, in dettaglio ne definisco una sottoclasse come *interna* al pannello
		// in modo da poter chiamare i metodi protetti di quest'ultimo
		new KeyListenerFrame(this).setVisible(true);		
		
	}
	
	// Aggiungo una forma nella posizione (x, y)
	protected void addShape(double x, double y)
	{
		
		shapes.add(new Ellipse2D.Double(
			x,
			y,
			getWidth() * 0.1,	// per far presto, le dimensioni sono fissate ad 1 decimo del pannello
			getHeight() * 0.1		
		));
		
	}
	
	// Questo metodo cancella tutte le forme
	protected void clearAll()
	{
		
		this.shapes.clear();
		
	}
	
	// Disegno tutte le forme della lista
	protected void drawShapes(Graphics2D g2)
	{
		
		g2.setColor(bgColor);
		
		for(int i = 0; i < shapes.size(); i++)
		{
			
			g2.fill(
				(Ellipse2D.Double)(shapes.get(i))
			);
			
		}
		
	}
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);
		
		drawShapes(g2);
				
	}
	
	// Nel main() costruisco solamente il pannello	
	public static void main(String[] args)
	{
		
		new KeyListenerPane();
		
	}
	
	// Il frame è interno al pannello, può accedere quindi ai suoi metodi e campi privati
	class KeyListenerFrame extends JFrame implements KeyListener
	{
		
		// Riferimento al pannello [classe esterna]
		protected KeyListenerPane pane;

		public KeyListenerFrame(KeyListenerPane pane)
		{
			
			super("Eventi tastiera.");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(pane);
			pack();
			
			this.pane = pane;
			
			// Il frame è sia ascoltatore di eventi di tastiera, che generatore dei medesimi
			addKeyListener(this);
			
		}
		
		// L'interfaccia KeyListener ha 3 metodi, dei quali qui viene implementato solo il primo
		public void keyPressed(KeyEvent ke)
		{
			
			// Quando viene premuto un tasto, viene generato un evento di tipo KeyEvent che racchiude tra le varie informazioni quella relativa al tasto premuto.
			// Vi si accede tramite metodo getKeyCode(), il quale restituisce il codice ASCII associato al carattere: tali codici sono salvati nella classe
			// KeyEvent come costanti statiche, vedere riferimenti nello switch seguente:
			
			switch(ke.getKeyCode())
			{
			
				// Se abbiamo premuto la p, apriamo un pannello per scegliere il colore:
				case KeyEvent.VK_P:
				
					pane.bgColor = JColorChooser.showDialog(this, "Scegli colore...", bgColor);
					pane.repaint();
				
					break;
				
				// Se abbiamo premuto la c, cancelliamo le forme:				
				case KeyEvent.VK_C:
				
					pane.clearAll();
					pane.repaint();
				
					break;
					
				// Se abbiamo premuto la r, generiamo una forma in posizione random:					
				case KeyEvent.VK_R:
				
					pane.addShape(
						Math.random() * pane.getWidth(),
						Math.random() * pane.getHeight()
					);
					pane.repaint();
				
					break;
				
			}
			
		}
		
		// Metodi privi di implementazione perché non necessaria in questo caso
		public void keyReleased(KeyEvent ke){}
		
		public void keyTyped(KeyEvent ke){}				
		
	}
	
	
}
