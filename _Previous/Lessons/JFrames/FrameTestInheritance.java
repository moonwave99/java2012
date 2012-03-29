import java.awt.*;
import javax.swing.*;

/*
*	Pressoché sempre, avrete necessità di *estendere* classi esistenti di Java per adattarle alle vostre necessità.
*	In dettaglio, andremo ora ad estendere JFrame per dotarla di un costruttore più ricco.
*/
class MioFrame extends JFrame{
	
	// Dotiamo il nostro JFrame di un'istanza della classe MioPanel:
	private MioPanel mp;
	
	// Il nostro costruttore vuole un titolo, due interi, un colore [che sarà del pannello];
	public MioFrame(String title, int width, int height, Color c){
		
		// Memento: qualora vogliate invocare un costruttore della superclasse, questa deve essere la prima istruzione del costruttore della classe ereditiera [che no, non annovera la signorina Hilton tra le proprie istanze]:
		super(title);
		
		// Chiamo i metodi del JFrame stesso [questa classe li eredita!]:
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creo un'istanza di MioPanel, il nostro JPanel on steroids:
		mp = new MioPanel(c);
		
		// Aggiungo il pannello al frame:
		this.getContentPane().add(mp);
		
		// Showtime:
		this.setVisible(true);	

	}
	
}

// Il nostro pannello prende un colore come parametro del costruttore, e lo pone come sfondo:
class MioPanel extends JPanel{

	public MioPanel(Color c){
		super();
		this.setBackground(c);
	}

	
}

public class FrameTestInheritance{
	
	public static void main(String[] args){

		// Ora è sufficiente invocare il costruttore della classe MioFrame con tutti i parametri del caso:
		MioFrame mf = new MioFrame(
			"Il mio frame!",
			640,
			480,
			Color.GREEN
		);
		
	}
	
}