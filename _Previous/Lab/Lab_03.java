public class Lab_03{

	public Lab_03(){
	
		// Una matrice in matematica è una tabella rettangolare di numeri [def. Wikipedia]. Definizione alternativa [ed informaticamente più calzante] è quella di "vettore di vettori":
		int[][] matrice = {
			{1, 0, 0},
			{0, 1, 0},
			{0, 0, 1}
		};
		
		// In Java una matrice di interi viene costruita nella seguente maniera:
		//
		//		int[][] matrice = new int[ numeroRighe ][ numeroColonne ];
		//
		//	Analogamente si possono creare matrici di float, double, nonché di oggetti ovviamente.
		
		// Una matrice si "naviga" tramite 2 cicli for concentrici: attenzione, è importante usare indici diversi [i, j] per ognuno dei due!
		
		// Creo una nuova matrice 3 x 5 [3 righe, 5 colonne]:
		int[][] matriceCasuale = new int[3][5];
		
		// Provvedo a riempirla con numeri casuali da 0 a 10:
		
		// Agisco riga per riga tramite l'indice i
		for(int i = 0; i < 3; i++){
			
			// Per ogni riga [cioè tenendo bloccata la i], mi sposto orizzontalmente incrementando la j
			for(int j = 0; j < 5; j++){
				
				// Scrivo sull'elemento i-j-esimo
				matriceCasuale[i][j] = (int)(Math.random() * 10);
				
			}
			
		}
		
		stampaMatrice(matriceCasuale);
		
		// Usando il solito vecchio metodo per generare vettori di interi casuali, possiamo fare così
		
		// Matrice vuota
		int[][] matriceNuova = new int[3][5];
		
		// Ad ogni vettore della matrice, assegno il vettore casuale generato dal metodo
		for(int i = 0; i < 3; i++){
			
			matriceNuova[i] = vettoreCasuale(0, 10, 5);
			
		}
		
		stampaMatrice(matriceNuova);

		// Vengono poi stampati gli esempi dei metodi visti in classe: identità, diagonaleVuota, uniAgliAngoli:
		
		int[][] id = identity(4);
		stampaMatrice(id);
		
		int[][] diagonale = diagonaleVuota(5);
		stampaMatrice(diagonale);		
		
		int[][] angoli = matriceAngoli(3, 4);
		stampaMatrice(angoli);
	
	}
	
	/*	Vogliamo generare una matrice siffatta:
	*
	*		1	0	0	0	1	
	*		0	0	0	0	0
	*		0	0	0	0	0
	*		1	0	0	0	1
	*
	*	Cioè con uni agli angoli.
	*/
	
	public int[][] matriceAngoli(int righe, int colonne){
		
		int[][] matrice = new int[righe][colonne];
		
		matrice[0][0] = 1;	
		matrice[0][colonne - 1] = 1;
		matrice[righe -1 ][0] = 1;
		matrice[righe - 1][colonne - 1] = 1;						
		
		return matrice;
		
	}

	/*	Il complementare [a 1] dell'identità:
	*
	*		0	1	1	1	
	*		1	0	1	1	
	*		1	1	0	1
	*		1	1	1	0
	*
	*	Tutti uni tranne 0 sulla diagonale ^^
	*/
	public int[][] diagonaleVuota(int n){
		
		int[][] matrice = new int[n][n];
		
		for(int i = 0; i < n; i++){
			
			for(int j = 0; j < n; j++){
				
				if( i == j ){
					
					matrice[i][j] = 0;
					
				}else{
					
					matrice[i][j] = 1;
					
				}

			}
			
		}	
		
		return matrice;
		
	}
	
	/*	La matrice identità è quella che ha uni sulla diagonale e 0 altrove:
	*
	*		1	0	0	0	
	*		0	1	0	0
	*		0	0	1	0
	*		0	0	0	1
	*
	*	Essa è quadrata per definizione, in quanto diagonale.
	*/	
	
	public int[][] identity(int n){
		
		// Genero una matrice di zeri n x n
		int[][] matrice = new int[n][n];
		
		// Mi muovo lungo la diagonale
		for(int i = 0; i < n; i++){
			
			matrice[i][i] = 1;
			
		}
		
		return matrice;
		
	}
	
	public void stampaMatrice(int[][] matrice){
		
		// Esploro le righe
		for(int i = 0; i < matrice.length; i++){
			
			// Esploro le colonne [i è costante dentro il ciclo interno]
			for(int j = 0; j < matrice[i].length; j++){
				
				// Stampo elemento i-j-esimo
				System.out.print( matrice[i][j] + " ");
				
			}
			
			// Vado a capo a fine di ogni riga
			System.out.print("\n");
			
		}
		
	}
	
	//	Una vecchia conoscenza
	public int[] vettoreCasuale(int min, int range, int dim)
	{
		
		int[] vettore = new int[dim];
		
		for(int i = 0; i < dim; i++){
			
			vettore[i] = (int)(Math.random() * range) + min;
			
		}
		
		return vettore;
		
	}
	
	public static void main(String[] args){
	
		new Lab_03();
	
	}


}