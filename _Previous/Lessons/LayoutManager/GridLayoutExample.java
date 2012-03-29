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

public class GridLayoutExample{

	public GridLayoutExample(){
	
		// Al solito, creo un nuovo frame, ne indico il titolo ed il comportamento alla chiusura:
		JFrame f = new JFrame("Esempio con GridLayout");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		*	Vediamo ora il GridLayout: esso disponde gli elementi in una griglia m x n [come in una matrice, già].
		*/

		// Stabilisco il numero di righe/colonne in 2 variabili, per maggiore comodità / leggibilità:
		int righe = 3;
		int colonne = 5;
		
		// Scelgo il Layout Manager:
		f.getContentPane().setLayout(new GridLayout(righe, colonne));

		// Riempio il frame con pannelli dallo sfondo casuale. I pannelli vengono inseriti da sx verso dx e dall'altro verso il basso:
		for(int i = 0; i < righe*colonne; i++){
			
			f.getContentPane().add(
				new MioPanel(new Color(
					(int)(255*Math.random()),
					(int)(255*Math.random()),
					(int)(255*Math.random())										
				),50, 50)
			);
			
		}
		
		// Impacchettiamo:
		f.pack();
		
		// Ammiriamo:
		f.setVisible(true);
	
	}

	public static void main(String[] a){
		new GridLayoutExample();
	}

}