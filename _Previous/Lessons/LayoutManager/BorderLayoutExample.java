import java.awt.*;
import javax.swing.*;

class MioPanel extends JPanel{
	
	public MioPanel(Color c, int width, int height){
		
		super();
		
		// Stabilisco il colore di sfondo del pannello
		this.setBackground(c);
		
		// Indico le dimensioni *preferenziali* del pannello - se l'elemento deputato alla resa del medesimo potrà rispettarle, esse saranno utilizzate.
		// La classe Dimension è semplicemente un wrapper di 2 interi, con qualche metodo a corredo [guardate la doc per ulteriori info].
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
}

public class BorderLayoutExample{

	public BorderLayoutExample(){
	
		// Al solito, creo un nuovo frame, ne indico il titolo ed il comportamento alla chiusura:
		JFrame f = new JFrame("Esempio con BorderLayout");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		*	Quando inserisco componenti in un Frame, posso indicare un gestore della disposizione [o Layout Manager].
		*	In questo caso utilizziamo BorderLayout, il quale permette di inserire elementi:
		*
		*		* Al centro - [BorderLayout.CENTER];
		*		* In alto	- [BorderLayout.NORTH];
		*		* In basso	- [BorderLayout.SOUTH];
		*		* A dx		- [BorderLayout.EAST];
		*		* A sx		- [BorderLayout.WEST].
		*
		*	Fate le prove del caso e vedrete da soli cosa accade.
		*/
		
		// In Java vetusto, dove passare per getContentPane() [ricordatelo sempre];
		f.getContentPane().setLayout(new BorderLayout());
		
		// Ora creo i miei MioPanel secondo necessità:
		MioPanel pannelloCentrale = new MioPanel(Color.RED, 400, 400);
		MioPanel pannelloLaterale = new MioPanel(Color.GREEN, 100, 400);		
		
		// Aggiungo i pannelli al frame, passando per getContentPane, ed indicando *dove* inserire i componenti all'interno del layout:
		f.getContentPane().add(
			pannelloCentrale,
			BorderLayout.CENTER	// Sto ponendo questo pannello al centro
		);
		
		f.getContentPane().add(
			pannelloLaterale,
			BorderLayout.WEST	// Sto ponendo quest'altro pannello a sx
		);
		
		// Il metodo pack() di JFrame indica allo stesso di adattare la propria dimensione in funzione del suo contenuto - è logico, è la cornice che deve adattarsi alla foto e non vicecersa.
		// Nota: se non avete stabilito la preferredSize dei singoli elementi potrete avere dei problemi, valutate di conseguenza.
		f.pack();
		
		// Vediamo cosa esce fuori:
		f.setVisible(true);
	
	}

	public static void main(String[] a){
		new BorderLayoutExample();
	}

}