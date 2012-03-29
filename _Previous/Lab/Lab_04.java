// La solita classe persona, con 3 campi e relativi getter/setter
class Persona{
	
	protected String nome;
	
	protected String cognome;	
	
	protected int age;
	
	// Costruttore blank
	public Persona(){ }
	
	// Costruttore con nome, cognome, età
	public Persona( String nome, String cognome, int age ){
		
		this.setNome( nome );		
		this.setCognome( cognome );
		this.setAge( age );
		
	}
	
	public void setNome(String nome){
		
		this.nome = nome;
		
	}
	
	public String getNome(){
		
		return this.nome;
		
	}
	
	public void setCognome(String cognome){
		
		this.cognome = cognome;
		
	}
	
	public String getCognome(){
		
		return this.cognome;
		
	}	
	
	// Qualora passi valori negativi, vengono privati del segno tramite il modulo
	public void setAge(int age){
		this.age = Math.abs(age);
	}
	
	public int getAge(){
		return this.age;
	}
	
	// Es. "Mario Brega - 72"
	public String toString(){
		
		return nome + " " + cognome + " - " + age;
		
	}
	
}

// Quando lanciate il programma, passategli i 3 argomenti - es. "java Lab_04 Mario Rossi 21", altrimenti darà errore [perché?]
public class Lab_04{
	
	public static void main(String[] args){
		
		
		// La Persona p viene costruita definendo esplicitamente i parametri
		Persona p = new Persona(
			"Mario",
			"Brega",
			72
		);
	
		// La Persona q invece li preleva dalla riga di comando
		Persona q = new Persona(
			args[0],
			args[1],
			Integer.parseInt( args[2] )
		);
		
		System.out.println(p);
		
		System.out.println(q);		
	
	}

}