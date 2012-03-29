import java.util.ArrayList;

public class Archivio{

	protected ArrayList dischi;

	public Archivio(){
	
		dischi = new ArrayList();
	
	}
	
	public void addDisco(Disco d){
	
		this.dischi.add(d);
	
	}
	
	public void stampaArchivio(){
	
		System.out.println("Questo archivio contiene "+this.dischi.size()+" dischi.");
		
		for(int i = 0; i < this.dischi.size(); i++){
		
			Disco d = (Disco)( this.dischi.get(i) );
		
			System.out.println(d);
		
		}

	}
	
	public ArrayList cercaPerAnno(int anno){
	
		ArrayList risultati = new ArrayList();
		
		for(int i = 0; i < this.dischi.size(); i++){
		
			Disco d = (Disco)( this.dischi.get(i) );
			
			if( d.getAnno() == anno ){
				
				risultati.add(d);
				
			}

		}
		
		return risultati;
	
	}
	
	public ArrayList cercaPerAutore(String key){
	
		ArrayList risultati = new ArrayList();
		
		for(int i = 0; i < this.dischi.size(); i++){
		
			Disco d = (Disco)( this.dischi.get(i) );

			if( d.isKeyValid(key) ){
			
				risultati.add(d);
				
			} 

		}
		
		return risultati;
	
	}	
	
	
	public static void main(String[] args){
	
		Archivio a = new Archivio();
		
		Autore[] listaAutori = new Autore[]{
		
			new Autore("Pink Floyd"),
			new Autore("Slowdive"),
			new Autore("Nick", "Drake"),			
			
		};

		a.addDisco(
			new Disco("Animals", listaAutori[0], 1977)
		);
		
		a.addDisco(
			new Disco("Meddle", listaAutori[0], 1975)
		);
		
		a.addDisco(
			new Disco("Pink Moon", listaAutori[2], 1977)
		);
		
		a.addDisco(
			new Disco("Souvlaki", listaAutori[1], 1993)
		);		
		
		//a.stampaArchivio(); 
		
		//**************************************************
		
		ArrayList risultati = a.cercaPerAutore("pink");
		
		System.out.println(risultati.size());
		

	}

}