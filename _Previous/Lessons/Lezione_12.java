import java.awt.event.*;
import javax.swing.Timer;

interface Running{

	public void run(int time);

}

class Automobile{

	protected double accelerazione;
	protected double vmax;
	
	protected double vel = 0;
	protected double distanza = 0;

	public Automobile(){}
	
	public Automobile(double accelerazione, double vmax){
		this.accelerazione = accelerazione;
		this.vmax = vmax;
	}
	
	public void run(int time){

		this.vel = this.accelerazione * time;
		
		if(kmOrari(this.vel) >= this.vmax){
		
			this.vel = metriSecondo(this.vmax);
			
		}
		
		this.distanza = 0.5 * this.vel * time;	

	}

	static double kmOrari(double metriSecondo){
	
		return metriSecondo * 3.6;
	
	}
	
	static double metriSecondo(double kmOrari){
	
		return kmOrari / 3.6;
	
	}
	
	public String toString(){
	
		return "Velocita' : " + kmOrari(this.vel) + " - Spazio percorso: " + this.distanza;
	
	}	

}

class Atleta extends Persona implements Running{

	protected double accelerazione;
	protected double vel = 0;
	protected double distanza = 0;

	public Atleta(){		
		super();
	}
	
	public Atleta(String nome, String cognome, double accelerazione){		
		super(nome, cognome);
		
		this.accelerazione = accelerazione;
	}
	
	
	// v = [m] / [t];
	// a = [m] / [t ^2];
				
	// v(t) = a * t;
	// s(t) = 1/2 a * t^2 + v0*t;	
	public void run(int time){
	
		this.vel = this.accelerazione * time;
		this.distanza = 0.5 * this.accelerazione * Math.pow(time, 2);	
	
	}

	public String toString(){
	
		return "Velocita' : " + this.vel + " - Spazio percorso: " + this.distanza;
	
	}

}


public class Lezione_12 implements ActionListener{

	private Timer t;
	private int secondi = 0;
	
	private Atleta a;
	private Automobile car;

	public Lezione_12(){
		
		a = new Atleta("Mario", "Rossi", 1.5);
		car = new Automobile(5, 120);
		
		t = new Timer(1000, this);
		t.start();
		
	}
	
	public void actionPerformed(ActionEvent e){
	
		secondi ++;

		//a.run(secondi);
		car.run(secondi);

		System.out.print(secondi + ": ");
		System.out.print(car);
		System.out.println();

	}
	
	public static void main(String[] args){
	
		new Lezione_12();
		
		javax.swing.JOptionPane.showConfirmDialog(null, "Premere CTRL + C per ucire.");
	
	
	}

}