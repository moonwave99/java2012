## Prova di animazione: caduta dei gravi.

Era ora che i nostri amati cerchi venissero inseriti in un contesto più reale di quello del mero trascinamento con il mouse: si trovano adesso soggetti alla forza di gravità, ma per loro fortuna non a quella di attrito con l'aria [per amor d'esempio].

Una visione d'insieme del programma è presto proposta:

	public class GravityPane extends JPanel implements Runnable, MouseListener, MouseMotionListener
	{

		// Istanza della classe Thread
		protected Thread th;

		...

		// Costruttore
		public GravityPane()
		{

			...

			JFrame f = new JFrame ...
			// Eventi tastiera
			f.addKeyListener(new KeyAdapter(){

				public void keyPressed(KeyEvent ke){ ... }

			});

			...

		}

		// Inizializer
		protected void init(){ ... }

		// Eventi mouse
		public void mouseDragged(){ ... }

		...

		// Metodi relativi al thread
		public void start(){ ... }
		public void stop(){ ... }
		public boolean isRunning(){ ... }
		public void run(){ ... }

		// Metodi propri del programma
		protected int getSelectedCircle(Point p){ ... }
		protected void createCircleOnPoint(Point p){ ... }
		protected void drawCircles(Graphics2D g2){ ... }
		protected void drawGround(Graphics2D g2){ ... }
		protected void drawPauseString(Graphics2D g2){ ... }

		// Paint
		public void paint(Graphics g){ ... }

		// Main
		public static void main(String[] args){ ... }

	}

A corredo abbiamo la solita classe ```Circle```, in questo caso dotata dei campi ```double vx``` e ```double vy``` contenenti le componenti della velocità lungo i due assi. Per simulare il comportamento di un corpo in caduta libera è sufficiente che esso si muova di **moto uniformemente accelerato** verso il basso: senza perdere troppo tempo nel bilanciare i valori reali dell'accelerazione **g** con i pixel dello schermo, vediamo cosa succede nel *loop* del metodo ```run()```:

	public void run()
	{

		while(isRunning())
		{

			for(int i = 0; i < circles.size(); i++)
			{

				// Se ho toccato terra [o se sto trascinando un cerchio!], assumo che la forza di gravità sia bilanciata dalla reazione vincolare del terreno
				if(circles.get(i).y + circles.get(i).height >= ground.y || i == selectedCircle)
					continue;

				// Incremento la velocità del corpo i-esimo, e la relativa posizione
				circles.get(i).vy += 0.1;
				circles.get(i).y += circles.get(i).vy;

			}

			repaint();

			try{

				Thread.sleep(refreshTime);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

Semplice: per ogni cerchio, ne incremento velocità e posizione previo controllo circa l'impatto al suolo. Per mantenere la funzionalità di trascinamento durante l'animazione, sono stati necessari alcuni controlli [commentati nel codice], la cui finalità è quella di azzerare la velocità dell'oggetto trascinato in modo da non farlo cadere.

Ultima parte è quella relativa agli eventi da tastiera:

	f.addKeyListener(new KeyAdapter(){

		public void keyPressed(KeyEvent ke)
		{

			switch(ke.getKeyCode()){

				// con la C cancello tutto
				case KeyEvent.VK_C:

					circles.clear();

					break;

				// con Spazio parto / mi fermo
				case KeyEvent.VK_SPACE:

					if(isRunning()) stop(); else start();

					break;

			}

			repaint();

		}

	});

Aggiunto al *frame*, l'ascoltatore di eventi è stavolta costruito in una maniera differente:

* tramite **classe annidata anonima**;
* mediante utilizzo di ```KeyAdapter```.

In generale un ```Adapter``` è una classe di comodo che implementa tutti i metodi di una data interfaccia, ma con corpo vuoto: in questo caso è soddisfatta la richiesta di avere un ```KeyListener``` per il metodo ```addKeyListener``` del frame, ed è possibile sovrascrivere il solo metodo di cui abbiamo bisogno in questo caso [```keyPressed()```] senza doverli implementare tutti. Tale approccio è molto comodo in fase di prototipazione, ma paga dazio in contesti più vasti per ovvi motivi.

Alla luce di questo semplice modello fisico si aprono numerosi scenari:

* far rimbalzare i cerchi al suolo;
* far soffiare del vento [forza orizzontale] il cui effetto agisce in funzione della massa dei singoli corpi;
* far cadere i corpi nell'acqua, e gestire la spinta di Archimede al momento dell'immersione.

Sta a voi divertirvi [^^] adesso, buon lavoro!

*D.*