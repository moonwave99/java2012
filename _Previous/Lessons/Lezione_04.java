public class Lezione_04
{

	public int[] trovaDivisori(int n)
	{
		
		int[] divisori = new int[(int)(n/2)+1];
		
		divisori[0] = 1;
		
		int k = 1;
		
		for(int i = 2; i <= (int)(n/2); i++){
		
			if( n % i == 0 ){
				divisori[k] = i;
				k++;
			}
		
		}
		
		divisori[k] = n;
	
		return divisori;
		
	}
	
	public boolean isPerfect(int n){
	
		int[] divisori = trovaDivisori(n);
		
		int somma = 0;
		
		System.out.println(divisori.length);
		System.out.println("");
		stampaVettore(divisori);
		
		for(int i=0; i < (int)(divisori.length) ;i++){
			
			somma += divisori[i];
	
			if(divisori[i] == 0){
			
				somma -= n;
			
				break;
			
			}
		
		}
		
		return somma == n;
	
	}
	
	public void stampaVettore(int[] v){
	
		for(int i=0;i<v.length;i++){
			System.out.println(v[i]);
		}
		
	}

	public Lezione_04(){
		
		

	}
	
	public static void main(String[] args){
		new Lezione_04();
	}

}