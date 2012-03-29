// Importo ArrayList per gestire i numeri associati alla scheda
import java.util.ArrayList;

public class Scheda{
	
	// Una scheda è costituita da *una* persona, e da *più* numeri [casa, cellulare, ufficio...]
	private Persona p;
	private ArrayList nums;
	
	// Costruttore blank
	public Scheda(){}
	
	// Costruttore con un solo numero
	public Scheda(Persona p, Numero n){

		// Inizializzo la lista
		nums = new ArrayList();
		
		// Assegno la persona
		this.p = p;
		
		// Aggiungo il Numero n
		this.addNum(n);
		
	}
	
	// Costruttore con più numeri
	public Scheda(Persona p, Numero[] ns){

		// Inizializzo la lista
		nums = new ArrayList();
		
		// Assegno la persona
		this.p = p;
		
		// Aggiungo i numeri
		for(int i = 0; i < ns.length; i++){
			this.addNum(ns[i]);
		}
		
	}
	
	// Get e set ordinari
	public void setPersona(Persona p){
		this.p = p;
	}
	
	public Persona getPersona(){
		return this.p;
	}
	
	// Invece di setNum(), in questi casi è preferibile scrivere un metodo che aggiunga un elemento alla volta
	public void addNum(Numero n){
		// Chiamo il metodo add(Object o) dell'ArrayList num per aggiungere il Numero n
		this.nums.add(n);
		
	}
	
	public ArrayList getNums(){
		return this.nums;
	}
	
	// La rappresentazione in stringa della scheda è data dal nome della persona, e dall'elenco dei numeri
	public String toString(){
	
		String s = this.p.getName() + "\n";
		
		for(int i = 0; i < this.nums.size(); i++){
			
			s += ((Numero)nums.get(i)).toString() + "\n";
			
		}
		
		return s;
		
	}
	
}