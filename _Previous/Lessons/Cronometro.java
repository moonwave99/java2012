import javax.swing.Timer;
import java.awt.event.*;

public class Cronometro implements ActionListener {

	public int secondi  = 0;	
	public int rovescia = -1;
	
	public Timer t;
	
	public Cronometro(){

		t = new Timer(1000, this);
	
	}
	
	public void actionPerformed(ActionEvent e){
	
		this.secondi ++ ;
		
		stampaTempo();
		
		if(secondi == rovescia){
		
			this.stop();
			
			System.out.println("fine");
		
		}

	}
	
	public void start(){
	
		t.start();
	
	}
	
	public void start(int rovescia){
	
		t.start();
		
		this.rovescia = Math.abs( rovescia );
	
	}	
	
	public void stop(){
	
		t.stop();
		
	}
	
	public void stampaTempo(){
	
		int tempo = 0;
	
		if(this.rovescia != -1){
		
			tempo = this.rovescia - this.secondi;
		
		}else{
		
			tempo = this.secondi;
			
		}

		int secondiDaStampare = tempo % 60;
		
		int minutiDaStampare = (int) (tempo / 60);
	
		System.out.println(
		
			minutiDaStampare + ":" + secondiDaStampare
		
		);	
	
	
	}

}