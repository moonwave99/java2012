import java.util.ArrayList;

public class Disco{

	protected String titolo;
	protected ArrayList autori;
	protected int anno;

	public Disco(){

		autori = new ArrayList();

	}
	
	public Disco(String titolo, Autore autore, int anno){
		
		autori = new ArrayList();
		
		this.setTitolo(titolo);
		this.addAutore(autore);
		this.setAnno(anno);
	
	}
	
	public Disco(String titolo, ArrayList autori, int anno){
		
		autori = new ArrayList();
		
		this.setTitolo(titolo);
		this.autori = autori;
		this.setAnno(anno);		
	
	}
	
	public String getTitolo(){ return this.titolo; }
	public void setTitolo(String titolo){ this.titolo = titolo; }
	
	public ArrayList getAutori(){ return this.autori; }
	
	public void addAutore(Autore autore){
		
		autori.add(autore);
		
	}
	
	public int getAnno(){ return this.anno; }
	public void setAnno(int anno){ this.anno = anno; }
	
	public String toString(){
	
		String s = "["+this.getAnno()+"] ";
		
		for(int i = 0; i < this.autori.size(); i++){
		
			Autore a = (Autore)(this.autori.get(i));
			
			s += a.toString()+" - ";
		
		}
		
		s += this.getTitolo();
		
		return s;
	
	}
	
	public boolean isKeyValid(String key){
	
		for(int i = 0; i < this.autori.size(); i++){
		
			Autore a = (Autore)(this.autori.get(i));
			
			if( a.isKeyValid(key) ){
			
				return true;
				
			}
		
		}		
		
		return false;
	
	}

}