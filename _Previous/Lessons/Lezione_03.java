public class Lezione_03{

	public int numeroCasuale(int inizio, int fine){
	
		int ampiezza = Math.abs(fine - inizio);
		
		return (int) (Math.random() * ampiezza + inizio);
		
	}
	
	public double numeroCasuale(double inizio, double fine){

		double ampiezza = Math.abs(fine - inizio);
		
		return Math.random() * ampiezza + inizio;
		
	}
	
	public int[] riempiVettore(int inizio, int fine, int dimensione){
	
		int[] temp = new int[dimensione];
		
		for(int i = 0; i < temp.length; i++){
		
			temp[i] = numeroCasuale(inizio, fine);
		
		}
		
		return temp;
	
	}
	
	public boolean isPrime(int n){
	
		boolean primo = true;
	
		for(int i = 2; i < n; i++){
			
			if(n % i == 0){
				primo = false;
				break;
			}
			
		}
	
		return primo;
		
	}
	
	public boolean ternaPitagorica(int a, int b, int c){
	
		return a*a == b*b + c*c;
		
	}
	
	public Lezione_03(){
	
		int a = 5;
		int b = 4;
		int c = 3;
	
		System.out.println(ternaPitagorica(a, b, c));
	
	}
	

	
	public static void main(String[] args){
		new Lezione_03();
	}

}