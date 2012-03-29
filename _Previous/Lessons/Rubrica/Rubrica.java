// È necessario importare la classe ArrayList per gestire l'elenco di schede
import java.util.ArrayList;

public class Rubrica{
	
	// Il numero delle schede è variabile, mi servo di un'ArrayList
	private ArrayList schede;
	
	// Costruisco una nuova rubrica: devo inizializzare l'ArrayList schede all'interno di questo metodo
	public Rubrica(){
		
		this.schede = new ArrayList();
		
	}
	
	// Aggiungo una scheda con un solo numero
	public void addScheda(Persona p, Numero n){
		
		schede.add(new Scheda(p, n));
		
	}

	// Aggiungo una scheda con più numeri: non passo un'ArrayList perché sebbene stia passando più numeri, il numero dei parametri è fissato
	public void addScheda(Persona p, Numero[] ns){

		schede.add(new Scheda(p, ns));
		
	}
	
	// Stampo le info sulla rubrica
	public void stampaRubrica(){
		
		System.out.println("La rubrica contiene "+ this.schede.size() +" voci.\n");
		
		for(int i = 0; i < schede.size(); i++){
			
			// Il metodo System.out.println(Object o) stampa il toString() dell'oggetto passato come parametro;
			// nel nostro caso abbiamo riscritto il toString() della classe Scheda:
			System.out.println(schede.get(i));
		}
		
	}
	
	public static void main(String[] args){
		
		// Inizializzo nuova rubrica:
		Rubrica r = new Rubrica();
		
		// Aggiungo una scheda dotata di numero singolo:
		r.addScheda(
			new Persona("Mario"),
			new Numero("666666", "Casa")
		);
		
		// Gino è un figo, ha anche un cellulare:
		r.addScheda(
			new Persona("Gino"),
			new Numero[]{
				new Numero("666666", "Casa"),
				new Numero("333333", "Mobile")
			}
		);
		
		/*
		*	È possibile inizializzare array fornendo il numero esatto di elementi in sede di allocazione, ad esempio:
		*
		*		int[] listaNumeri = {2, 3, 5, 7, 11, 13};
		*
		*	Nel caso precedente tale inizializzazione è prodotta in maniera *anonima*, senza cioè dichiarare esplicitamente il vettore - ad es:
		*
		*		new int[]{1, 2, 3};
		*
		*	In questo caso il vettore era di oggetti Numero, ergo:
		*
		*		new Numero[]{ listaElementi... }
		*
		*	La listaElementi a sua volta è composta da elementi anonimi, visto che non ho necessità di scrivere esplicitamente tutte le variabili.
		*	
		*	In ogni modo per ora non fate danno a dichiarare tutto esplicitamente, anzi è un buon esercizio per produrre codice ordinato; in un futuro spero prossimo imparerete a gioire del codice criptico e minimale, vezzo di ogni programmatore che si rispetti.
		*
		*/
		
		// Stampo la rubrica:
		r.stampaRubrica();
		
	}
	
}