public class Persona{
	
	protected String name;
	protected String lastName;

	public Persona(){}
	
	public Persona(String name, String lastName){
		
		this.setName(name);
		this.setLastName(lastName);		
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}	

	public String toString(){
		return this.getName() + " " + this.getLastName();
	}
	
	public Object clone(){
		
		return  new Persona(
			this.getName(),
			this.getLastName()
		);
		
	}
	
}	