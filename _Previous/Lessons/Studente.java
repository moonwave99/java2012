public class Studente extends Persona implements Comparable{

	protected int matricola;

	public Studente(){}
	
	public Studente(String name, String lastName){
		
		super(name, lastName);
		
	}
	
	public Studente(String name, String lastName, int matricola){
		
		super(name, lastName);
		
		this.matricola = matricola;
		
	}
	
	public void setMatricola(int matricola){
		this.matricola = matricola;
	}
	
	public int getMatricola(){
		return this.matricola;
	}
	
	
	public String toString(){
		return super.toString() + " - " + this.getMatricola();
	}
	
	public boolean equals(Studente s){
	
		return this.getMatricola() == s.getMatricola();
	
	}
	
	public int compareTo(Object o){
	
		Studente altro = (Studente)o;
	
		return this.lastName.compareTo(altro.getLastName());
	
	}
	
}