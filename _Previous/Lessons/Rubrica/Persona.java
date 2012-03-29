// Classe essenziale - un campo con get e set associati e due costruttori [di cui uno blank]
public class Persona{
	
	private String name;
	
	public Persona(){}
	
	public Persona(String name){
		
		this.name = name;
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}	
	
}