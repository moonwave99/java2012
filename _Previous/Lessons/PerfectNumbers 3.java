// In questo programma sviluppiamo un test elementare sui numeri perfetti, i numeri cioè pari alla somma dei propri divisori tranne se stessi [es. 6, 28];

public class PerfectNumbers{
	
	// La ricerca dei divisori di n restituirà un vettore di numeri interi;
	public int[] findDividers(int n){
		
		// Inizializzo il vettore dei risultati come un vettore di n / 2 + 1 componenti;
		// tale limite superiore non è ottimale ma per ora non possiamo fare di meglio.
		int[] dividers = new int[(int)(n*0.5f) + 1];

		// Il numero 1 divide tutti i numeri, perché a = 1*a, qualsiasi a intero.
		dividers[0] = 1;
		
		// Inizializzo un contatore temporaneo, che incrementerò ad ogni divisore trovato:
		int k = 1;
		
		// Inizia il viaggio sulla retta degli interi, ultima fermata n / 2 [perché?]:
		for (int i = 2; i <= (int)(n*0.5f); i++){
			
			// Se i divide n, allora n = i * q con resto 0 => n % i = 0!
			if(n % i == 0){
				
				// Salvo il numero i alla posizione k:
				dividers[k] = i;
				
				// Avanti il prossimo!
				k++;
			}
				
		}
		
		// Ogni intero divide se stesso [la relazione di divisibilità è *riflessiva*]: 
		dividers[k] = n;
		
		// Restituisco i divisori cercati:
		return dividers;
		
	}
	
	// Verifico se il numero intero n è perfetto, cioè equivale alla somma dei propri divisori tranne se stesso:
	public boolean isPerfect(int n){
		
		// Sfrutto la funzione precedente per trovare i divisori;
		int[] dividers = findDividers(n);
		
		// Al solito, inizializzo la somma a 0:
		int sum = 0;
		
		// Procedo alla somma iterativa dei divisori;
		for(int i = 0; i < dividers.length; i++){
			
			sum += dividers[i];

			// Appena trovo uno zero mi fermo!
			if(dividers[i] == 0){
				break;
			}
			
		}
		
		// Sottraggo il numero stesso dalla sommma dei divisori [secondo la definizione di perfetto]. Alternativamente, potevo confrontare sum e 2*n, chiaro.
		sum -= n;
		
		// Restituisco il confronto tra la somma ed il numero:
		return sum == n;
		
	}

	public PerfectNumbers(){
		// Stampo il test per alcuni numeri: i primi non sono mai perfetti per definizione, hanno per divisori solo 1 e loro medesimi!
		System.out.println(11+" "+isPerfect(11));
		System.out.println(57+" "+isPerfect(57));		
		System.out.println(108+" "+isPerfect(108));		
		
		// I primi membri del club:
		System.out.println(6+" "+isPerfect(6));		
		System.out.println(28+" "+isPerfect(28));		
		System.out.println(496+" "+isPerfect(496));
	}
	
	public static void main(String[] args){
		new PerfectNumbers();
	}


}