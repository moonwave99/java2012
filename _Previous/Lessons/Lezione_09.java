

public class Lezione_09{

	public Lezione_09(){
	
		Persona p = new Persona("a", "a");
		
		
		
		Persona q = (Persona)p.clone();
			p.setName("b");	
		System.out.println(q);
		
	}
	
	public static void main(String[] args){
	
		new Lezione_09();
	
	}
	
}