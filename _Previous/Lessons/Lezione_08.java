public class Lezione_08{

	public Lezione_08(){
		
		Course c = new Course("Scienze Media");
		
		Studente s = new Studente("Alessandro", "Manzoni", 123456);
		Studente t = new Studente("Cesare", "Beccaria", 123456);
		
		c.addStudent(s);
		c.addStudent(t);
				
		c.stampaElencoIscritti();
		
		/*Studente risultato = c.findByMatricola(12345);
		
		if(risultato != null){
			System.out.println(risultato);
		}else{
			System.out.println("Nessun risultato trovato.");
		}*/
		
	}
	
	public static void main(String[] args){
	
		new Lezione_08();
	
	}
	
}