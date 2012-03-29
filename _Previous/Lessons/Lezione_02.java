public class Lezione_02{

	public double dividi(double dividendo, double divisore){
		return dividendo / divisore;
	}
	
	public int fattoriale( int n ){

		int risultato = 1;

		for( int i = 1; i<=n; i++){
			risultato *= i;
		}

		return risultato;

	}

	public int fattorialeRicorsivo( int n ){

		while(n > 0){
			return n*fattorialeRicorsivo(n-1);
		}

		return 1;

	}
	

	public Lezione_02(){
		
		System.out.println(fattorialeRicorsivo( 5 ));
		System.out.println(fattoriale( 5 ));
		//n! + n * (n-1)!
		//0! = 1;

		System.out.println(dividi(5, 2));

	}

	public static void main(String[] args){
		new Lezione_02();
	}

}