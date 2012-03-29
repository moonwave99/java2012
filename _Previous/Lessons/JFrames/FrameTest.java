// Importiamo le classi necessarie per gestire l'interfaccia grafica [d'ora in poi GUI - Graphical User Interface]
import java.awt.*;		// Abstract Window Toolkit, la prima libreria java per componenti grafici [in ordine cronologico];
import javax.swing.*;	// Swing, l'evoluzione attuale dei componenti grafici

public class FrameTest{
	
	public static void main(String[] args){

		//	Un JFrame è una cornice all'interno della quale vengono collocati elementi della GUI;
		//	esso è corredato di pulsanti di chiusura/ingrandimento e di un meccanismo di ridimensionamento [come in tutte le applicazioni che già conoscete].
		JFrame f = new JFrame();
		
		//	Assegno un titolo alla finestra;		
		f.setTitle("Il mio primo frame");
		
		//	Assegno la dimensione al frame - vedremo che questa pratica è poco perfomante, perché stabilisce le dimensioni della finestra *compresa* la barra del titolo, dimensione che varia da SO a SO.
		//	Presto scopriremo un modo per adattare la cornice alle dimensioni del contenuto [metodo più assennato].
		f.setSize(640,480);
		
		//	Stabilisco che l'applicazione corrente [FrameTest] termini con la chiusura del frame f.
		//	Il parametro passato è una costante statica della classe JFrame - vedere la documentazione per comportamenti differenti.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Aggiungiamo un elemento alla cornice - creiamo una tela vuota, chiamata JPanel in Java:
		JPanel p = new JPanel();
		
		//	Affinché la presenza del pannello sia percepita, diamogli un colore di sfondo:
		//	la costante Color.RED è un campo statico della classe Color, contenente la terna (255,0,0) ovviamente.
		//	Mi raccomando consultate la documentazione relativa a java.awt.Color, e prendete [ulteriore] dimestichezza con le modalità per rappresentare un colore in spazi numerici.
		p.setBackground(Color.RED);
		
		//	Abbiamo creato il pannello, ora dobbiamo aggiungerlo alla cornice. Nelle versioni meno preistoriche di Java potete fare direttamente f.add(JPanel p), ma con i terminali delle aule siamo costretti al giurassico:
		//		
		//		f.getContentPane().add(p);
		//
		//	il metodo getContentPane() di JFrame restituisce un oggetto Container, responsabile della disposizione degli elementi aggiunti al JFrame stesso - per ora aggiungete tale chiamata senza farvi troppe domande.
		//	Ricordo che se sulle macchine delle aule non invocherete getContentPane(), riceverete un errore circa l'assenza di un metodo add() della classe JFrame, occhio!
		f.getContentPane().add(p);
		
		//	Visualizzo finalmente il frame - qualora inconstraste il vetusto "f.show()", sappiate che è un metodo deprecato da tempo.
		f.setVisible(true);

		
	}
	
}