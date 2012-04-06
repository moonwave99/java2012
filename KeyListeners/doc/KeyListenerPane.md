## Eventi da tastiera: implementazione dell'interfaccia KeyListener

Nell'ultima lezione abbiamo visto come gestire eventi da tastiera: a tale compito è deputata l'interfaccia```KeyListener```, la quale è dotata di tre metodi:

	public void keyPressed(KeyEvent ke){}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	
Finora abbiamo gestito gli eventi del mouse tramite le interfacce ```MouseListener``` e ```MouseMotionListener``` direttamente dal ```JPanel``` corrispondente - stavolta useremo una strategia differente, rendendo il ```JFrame``` ascoltatore e generatore di eventi.

Per prima cosa, scriviamo una classe  ```KeyListenerPane``` con il programma base [implementazione effettiva nel sorgente]:

	public class KeyListenerPane extends JPanel
	{
		
		public KeyListenerPane( ... ){ ... }
		
		protected void addShape( ... ){ ... }
		
		protected void clearShapes( ... ){ ... }
		
		protected void drawShapes( ... ){ ... }
		
		public void paint(Graphics g ){ ... }
		
		public static void main(String[] args){ ... }
		
	}
	
A questo punto, definiamo una classe ```KeyListenerFrame``` **interna** al pannello, al costruttore della quale passiamo un riferimento al pannello stesso:

	class KeyListenerFrame extends JFrame implements KeyListener
	{
	
		protected KeyListenerPane pane;

		public KeyListenerFrame(KeyListenerPane pane)
		{
		
			this.pane = pane;
			
			...
			
			this.addKeyListener(this);
			
		}
	
		public void keyPressed(KeyEvent ke){ ... }
		public void keyReleased(KeyEvent ke){ ... }
		public void keyTyped(KeyEvent ke){ ... }				
	
	}
	
Ora creiamo un ```KeyListenerFrame``` nel costruttore del pannello: il programma sarà pronto a recepire input da tastiera [fatevi stampare ```ke.getKeyCode()```] nel metodo ```keyPressed()``` come prova: vedrete che verrà stampato il codice ASCII [pron. 'ask-ii'!] associato al carattere premuto - operando uno ```switch``` su tale numero potete controllare il flusso logico.
Molto probabilmente non conoscerete i codici ASCII [pron. 'ask-ii', repetita iuvant] dei caratteri: vi viene in soccorso la classe  ```KeyEvent ```, le cui costanti raccolgono tali codici [es. il codice per la lettera A è  ```KeyEvent.VK_A ```, quello per la lettera B  ```KeyEvent.VK_B ```, etc.].

A questo punto, potete chiamare i metodi del pannello all'interno di ```keyPressed()```, associandoli ad una lettera della tastiera in particolare magari:

	public void keyPressed(KeyEvent ke)
	{
		
		switch(ke.getKeyCode())
		{
		
			// Se abbiamo premuto la p, apriamo un pannello per scegliere il colore:
			case KeyEvent.VK_P:
			
				pane.bgColor = JColorChooser.showDialog(this, "Scegli colore...", bgColor);
				pane.repaint();
			
				break;
			
			// Se abbiamo premuto la c, cancelliamo le forme:				
			case KeyEvent.VK_C:
			
				pane.clearAll();
				pane.repaint();
			
				break;
				
			// Se abbiamo premuto la r, generiamo una forma in posizione random:					
			case KeyEvent.VK_R:
			
				pane.addShape(
					Math.random() * pane.getWidth(),
					Math.random() * pane.getHeight()
				);
				pane.repaint();
			
				break;
			
		}
		
	}
	
Ora potrete integrare questa nuova tecnica nei programmi passati, unendo l'interazione tramite mouse a quella tramite tastiera, e gestendo così modalità di input più ricche.

*D.*