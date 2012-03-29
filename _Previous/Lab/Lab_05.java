class Studente extends Persona{
	
	protected int matricola;
	
	public Studente(String nome, String cognome, int age, int matricola){

		super(nome, cognome, age);
		
		this.matricola = matricola;

	}
	
	public int getMatricola(){ return this.matricola; }
	
	public String toString(){
		
		return super.toString() + " : " + this.matricola;
		
	}
	
	public boolean equals(Studente other){
		
		return super.equals(other) && ( this.matricola == other.getMatricola() );
		
	}
	
}

class Persona{
	
	protected String nome;
	protected String cognome;
	protected int age;
	
	public Persona(){}
	
	public Persona(String nome, String cognome, int age){
		this.setNome(nome);
		this.setCognome(cognome);
		this.setAge(age);
	}
	
	public void setNome(String nome){ this.nome = nome;}
	public String getNome(){ return this.nome;}

	public void setCognome(String cognome){ this.cognome = cognome;}
	public String getCognome(){ return this.cognome;}
	
	public void setAge(int age){ this.age = Math.abs(age);}
	public int getAge(){ return this.age;}		
	
	public String toString(){

		return this.nome + " " + this.cognome + " - " + this.age;

	}
	
	public boolean equals(Persona other){
		
		return
			this.getNome() == other.getNome() &&
			this.getCognome() == other.getCognome() &&			
			this.getAge() == other.getAge();

	}
	
}

public class Lab_05{

	public static void main(String[] args){
		
		Persona p = new Persona("Mario", "Brega", 72);
		
		//System.out.println(p);	
		
		Studente s = new Studente("Gino", "Capponi", 20, 946749867);
		Studente t = new Studente("Gino", "Capponi", 20, 946749867);

		System.out.println(s);	
		System.out.println(t);	
		
		System.out.println(s.equals(t));	
				
	}
	
}