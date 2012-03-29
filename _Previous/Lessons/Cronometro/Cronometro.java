// Questi sono gli import necessari - il Timer, ed il package che gestisce gli eventi
import javax.swing.Timer;
import java.awt.event.*;

// La classe Cronometro implementa l'inferfaccia ActionListener - ne deve quindi ridefinire tutti i metodi, come indicato nella documentazione a riguardo
public class Cronometro implements ActionListener{
	
	public int secondi = 0; // Il conto dei secondi;
	public int timeOut = -1;// Il conto alla rovescia - di default è posto = a -1, poi vediamo perché;
	public Timer t;			// Il Timer che notificherà il cronometro del tempo trascorso.

	public Cronometro(){
	
		// Un Timer è un oggetto che ogni tot millisecondi parla al proprio ascoltatore dicendogli "Ehi, sono passati tot millisecondi", e ne invoca il metodo actionPerformed().
		t = new Timer(1000, this);
		
	}
	
	// Costruttore per eventuale conto alla rovescia
	public Cronometro(int timeOut){
		
		t = new Timer(1000, this);
		
		this.timeOut = timeOut;
		
	}
	
	// Facciamo partire il cronometro
	public void start(){
		
		this.t.start();
		
	}
	
	// Metodo per fermare il cronometro [utile per il conto alla rovescia]
	public void stop(){
		
		this.t.stop();
		
	}

	// Ogni secondo, il timer chiama questo metodo della classe Cronometro
	public void actionPerformed(ActionEvent e){
		
		secondi ++;						// Incremento il numero dei secondi trascorsi
		
		int secondiDisplay;
		
		if(timeOut != -1){
			
			secondiDisplay = timeOut - secondi;
			
			if(secondiDisplay == 0){
				
				this.stop();
				System.out.println("Fine!");
				
			}
				
			
		}else{
			
			secondiDisplay = secondi;
			
		}

		System.out.println(secondiDisplay);	// Li stampo a terminale
		
	}
	
	public static void main(String[] args){
		
		Cronometro c = new Cronometro(10);	// Creo un nuovo cronometro
		c.start();							// Lo faccio partire
		
		// Questo hack serve a tenere "vivo" il programma - dopo aver premuto OK sul popup, premete CTRL+C per bloccare il programma
		javax.swing.JOptionPane.showMessageDialog(null, "Premere CTRL + C per bloccare il programma!");
		
	}

}