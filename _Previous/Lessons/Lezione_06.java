import java.util.ArrayList;

class Rubrica{

	ArrayList schede;

	public Rubrica(){

		schede = new ArrayList();
		
	}
	
	public void addScheda(Persona p, Numero n){
		
		Scheda s = new Scheda();
		s.setPersona(p);
		s.addNumero(n);
		
		schede.add(s);
		
	}
	
	public void addScheda(Persona p, Numero[] numeri){
	
		Scheda s = new Scheda();
		s.setPersona(p);
		
		for(int i = 0; i < numeri.length; i++){
			
			s.addNumero(numeri[i]);
		
		}
		
		schede.add(s);
		
	}
	
	public void stampaRubrica(){
		
		System.out.println("La rubrica contiene "+schede.size()+" voci");
		
		for(int i = 0; i < schede.size(); i++){
		
			System.out.println(
				(Scheda)schede.get(i)
			);
		
		}
		
	}

}

class Scheda{

	private Persona p;
	
	private ArrayList numeri;
	
	public Scheda(){
	
		numeri = new ArrayList();
		
	}
	
	public void setPersona(Persona p){
		this.p = p;
	}
	
	public Persona getPersona(){
		return this.p;
	}
	
	public void addNumero(Numero n){
		numeri.add(n);
	}
	
	public ArrayList getNumeri(){
		return this.numeri;
	}
	
	public String toString(){
	
		String s = "Nome: "+this.p.getName()+"\n";
		
		for(int i = 0; i < numeri.size(); i++){
		
			s += ((Numero)(numeri.get(i))).toString()+"\n";
			
		}
	
		return s;
		
	}

}

class Persona{

	private String name;
	
	public Persona(){
	
	}
	
	public Persona(String name){
		this.setName(name);
	}
	
	public String getName(){return this.name;}
	public void setName(String name){this.name = name;}

}

class Numero{

	private int numero;
	private String label;
	
	public Numero(){
	
	}
	
	public Numero(int numero, String label){
		this.setNumero(numero);
		this.setLabel(label);
	}
	
	public String getLabel(){return this.label;}
	public void setLabel(String label){this.label = label;}

	public int getNumero(){return this.numero;}
	public void setNumero(int numero	){this.numero = numero;}
	
	public String toString(){
		
		return this.getLabel()+": "+this.getNumero();
		
	}

}

public class Lezione_06{

	public Lezione_06(){
		
		Rubrica r = new Rubrica();

		r.addScheda(
			new Persona("Gino"),
			new Numero(012345, "Casa")
		);
		
		Numero[] numeriMario = {
			new Numero(654321, "Casa"),
			new Numero(123456, "Ufficio")
		};
		
		r.addScheda(
			new Persona("Mario"),
			numeriMario
		);
		
		r.stampaRubrica();

	}
	
	public static void main(String[] args){
	
		new Lezione_06();
	}

}