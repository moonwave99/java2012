interface Parlante{

	public String parla();

}

abstract class EssereVivente{

	public String nome;

	public String toString(){
	
		return nome;
		
	}

}

abstract class Animale extends EssereVivente{



}

class Cane extends Animale implements Parlante{

	public String parla(){
		
		return "bau";

	}

	public String toString(){
	
		return super.toString() + ", sono un cane.";
		
	}

}

public class Lezione_10{
	
	public Lezione_10(){	

		Cane c = new Cane();
		
		c.nome = "Fido";
		
		System.out.println(c);
		
		c.parla();

	}

	public static void main(String[] args){
		new Lezione_10();
	}

}