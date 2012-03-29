public class Autore extends Persona{

	public Autore(){
		
	}

	public Autore(String name){
		super(name, "");
	}
	
	public Autore(String name, String lastName){
		super(name, lastName);
	}
	
	public boolean isKeyValid(String key){
	
		return 
		this.toString().
			toLowerCase().
			startsWith(
				key.toLowerCase()
			);
	
	}

}