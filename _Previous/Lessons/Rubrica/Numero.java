// Questa classe è molto semplice: contiene due campi [numero ed etichetta], ognuno con get e set, ed un metodo toString().
public class Numero{
	
	private String number;
	private String label;
	
	public Numero(){}
	
	public Numero(String number, String label){
		
		this.number = number;
		this.label = label;
		
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public String toString(){
		return this.label + ": " + this.number;
	}
	
}