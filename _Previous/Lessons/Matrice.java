
public class Matrice{
	
	public static void main(String[]args){
		
		int [][] matrice=getMatrix(4);
		printMatrix(matrice);
		
	}//main
	
	
	public static int[][] getMatrix(int dim){
		
		int[][] matrice=new int[dim][dim];
		
		//riempiamo la matrice di 1
		/*for(int y=0 ; y<matrice.length; y++){
			
			for(int x=0 ; x<matrice[y].length ; x++){
				
				matrice[y][x]=1;
				
			}//for sulle colonne (x)
			
		}//for sulle righe (y)*/
		
		//otteniamo la matrice identitˆ (modo1)
		/*for(int y=0 ; y<matrice.length; y++){
			
			for(int x=0 ; x<matrice[y].length ; x++){
				
				if(x==y){
					matrice[y][x]=1;
				}
				
			}//for sulle colonne (x)
			
		}//for sulle righe (y)*/
		
		//otteniamo la matrice identitˆ (modo2)
		/*for(int i=0 ; i<matrice.length ; i++){
			matrice[i][i]=1;
		}*/
		
		//zero sulla diagonale 1 altrove
		/*for(int y=0 ; y<matrice.length; y++){
			
			for(int x=0 ; x<matrice[y].length ; x++){
				
				if(x==y){
					matrice[y][x]=0;
				}else{
					//x!=y
					matrice[y][x]=1;
				}
				
			}//for sulle colonne (x)
			
		}//for sulle righe (y)*/
		
		
		for(int y=0 ; y<matrice.length; y++){
			
			for(int x=0 ; x<matrice[y].length ; x++){
				
				int somma=0;
				
				for(int yy=0 ; yy<matrice.length ; yy++){
					
					for(int xx=0 ; xx<matrice[yy].length;xx++){
						somma+=matrice[yy][xx];
					}
					
				}
				
				if(somma==0){
					somma=1;
				}
				
				matrice[y][x]=somma;
				
			}//for sulle colonne (x)
			
		}//for sulle righe (y)
		
		
		return matrice;
		
	}//getMatrix
	
	
	public static void printMatrix(int [][] matrice){
		
		for(int y=0 ; y<matrice.length; y++){
			
			for(int x=0 ; x<matrice[y].length ; x++){
				
				System.out.print(matrice[y][x]+" ");
				
			}//for sulle colonne (x)
			
			System.out.println("");
			
		}//for sulle righe (y)
		
	}//printMatrix

	
}//Matrice
