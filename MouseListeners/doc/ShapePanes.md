## Disegno e trascinamento di forme: gestione di un numero arbitrario di esse

Nella lezione precedente abbiamo gestito il trascinamento di una singola figura, già presente al momento della creazione del pannello. Stavolta vogliamo che il nostro programma:

* permetta di creare una nuova forma ad ogni click;
* permetta di trascinare le forme esistenti;
* evidenzi la forma selezionata al passaggio del mouse;
* permetta di cancellare dal pannello forme esistenti.

Sfruttando ancora l'interfaccia ```DraggableShape``` creata prima, definiamo non più una singola istanza ```shape``` bensì un'```ArrayList``` deputata a contenere le forme che vengono create man mano.

Per la semplice creazione, è sufficiente che nel metodo ```mousePressed()``` aggiungiamo una nuova forma alla lista:

	public void mousePressed(MouseEvent me)
	{
		
		this.shapes.add(new ColoredCircle(
			me.getPoint().x - 50,
			me.getPoint().y - 50,
			50,
			Color.RED
		));
		
		repaint();
		
	}

Chiaro no? Una gestione così semplice genera una serie di problemi, primo fra essi il comportamento del programma quando si clicca su un cerchio già esistente! Dobbiamo quindi sapere se stiamo cliccando su una forma o su una porzione di spazio non ancora occupata. Visto che il calcolatore non ha percezione spaziale del problema, sfruttiamo la sua potenza di calcolo all'interno di un metodo apposito:

	protected int getSelectedShape(Point p)
	{

		for(int i = 0; i < shapes.size(); i++)
		{
		
			if(shapes.get(i).contains(p)){
			
				return i;
			
			}
		
		}

		return -1;
	
	}	

Facile: se ho cliccato su qualcosa, mi segno l'indice che tale figura ha all'interno dell'```ArrayList```, altrimenti mi segno un bel ```-1```, che è convenzione per **nessun elemento selezionato**. Quando invoco tale metodo? La scelta migliore è quella di chiamarlo dentro ```mouseMoved()```:

	public void mouseMoved(MouseEvent me)
	{
		
		this.selectedShape = this.getSelectedShape(me.getPoint());
		
	}
	
A questo punto gestiamo tutto in funzione dell'elemento selezionato, ad esempio:

	public void mouseDragged(MouseEvent me)
	{
		
		if(this.selectedShape != -1){
			
			this.shapes.get(this.selectedShape).move(me.getPoint());
			repaint();
			
		}
		
	}
	
Vedere i metodi ```mouseClicked()```, ```mousePressed()``` e ```mouseReleased()``` per il resto dell'implementazione.

Abbiamo poi dotato l'interfaccia ```DraggableShape``` del metodo ```highlight()``` per gestire il passaggio del mouse sulle forme, ed abbiamo aggiornato le classi che la implementano di conseguenza. In dettaglio, ```ColoredCircle``` è diventata:

	class ColoredCircle extends Circle
	{
	
		protected Color bgColor;
		protected boolean selected = false;	
		
		//...
	
		public void draw(Graphics2D g2)
		{
		
			g2.setColor( selected ? new Color(
		
				this.bgColor.getRed(),
				this.bgColor.getGreen(),
				this.bgColor.getBlue(),
				255
			
			) : this.bgColor);
		
			g2.fill(this);
		
		}	
	
		public void highlight(boolean on)
		{
		
			this.selected = on;
		
		}	
	
	}
	
Il colore del cerchio è funzione del campo ```selected```, che viene impostato dal metodo ```highlight()``` del pannello. Nota teorica, il comportamento di tale metodo è quello della *delta di Kronecker*, una funzione che, fissato un valore, vale 1 in corrispondenza di esso e 0 altrove; nel nostro caso appunto evidenzia l'elemento di cui passiamo l'indice e *spegne* gli altri.

Ora che sappiamo gestire una quantità variabile di forme, si aprono vari scenari:

* disegnare l'elemento selezionato sopra tutti gli altri;
* selezionare un numero arbitrario di elementi [es. tramite strumento di selezione rettangolare];
* disegnare forme differenti in funzione di un parametro.

Per qualsiasi dubbio non esitate a contattarmi via e-mail - buon lavoro,

*D.*