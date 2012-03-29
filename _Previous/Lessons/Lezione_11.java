import javax.swing.Timer;
import java.awt.event.*;

class CronometroEsterno{

	public int secondi = 0;
	public Timer t;
	
	public AzioneEsterna ae;
	
	public AzioneInterna ai;

	public CronometroEsterno(){
	
		//ae = new AzioneEsterna( this );
		
		ai = new AzioneInterna();
		
		//t = new Timer( 1000, ai );
		
		t = new Timer( 1000, new ActionListener(){
		
			public void actionPerformed(ActionEvent e){
			
				System.out.println(e);
			
			}
		
		});

	
	}
	
	public void start(){
		t.start();
	}
	
	public void stampaTempo(){
	
		System.out.println(this.secondi);
	
	}
	
	private class AzioneInterna implements ActionListener{

		public AzioneInterna(){
	
		}
	
		public void actionPerformed(ActionEvent evento){
	
			secondi ++;
		
			stampaTempo();
	
		}
	
	}

}

class AzioneEsterna implements ActionListener{

	protected CronometroEsterno c;

	public AzioneEsterna(CronometroEsterno c){
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent evento){
	
		c.secondi ++;
		
		c.stampaTempo();
	
	}


}


public class Lezione_11{

	public Lezione_11(){

		CronometroEsterno c = new CronometroEsterno();
		
		c.start();
		
	}
	
	public static void main(String[] args){
	
		new Lezione_11();
		
		javax.swing.JOptionPane.showConfirmDialog(null, "Termina Programma?");
	
	}
	
}