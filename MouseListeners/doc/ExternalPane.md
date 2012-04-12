## Ancora su eventi: ascoltatori esterni

Ultimo esempio, la gestione degli eventi tramite ascoltatore *esterno* al pannello.

Stavolta è la classe ```OuterListener``` ad implementare le interfacce: ad essa viene passato un riferimento al pannello, affinché ne possa vedere metodi e campi protetti.

	class OuterListener implements MouseListener, MouseMotionListener
	{
	
		protected ExternalPane pane;
	
		public OuterListener(ExternalPane pane)
		{
		
			this.pane = pane;
		
		}
	
		public void mousePressed(MouseEvent me)
		{
		
			if(pane.getElli().contains(me.getPoint()))
			{
			
				pane.getElli().setPinPoint(me.getPoint());
				pane.setDragging(true);
			
			}		
		
		}
		
		...
	
	}
	
Notare come nel metodo ```mousePressed``` non si faccia direttamente riferimento a ```pane.elli``` [è un campo protetto!], bensì si passi per il *getter* ```getElli()``` - idem per la modifica del valore ```dragging```.

Il pannello è stato quindi dotato dei seguenti:

	public MyEllipse getElli(){ return elli; }
	public boolean isDragging(){ return dragging; }
	public void setDragging(boolean dragging){ this.dragging = dragging; }
	
**Vantaggi**:
* classe disaccoppiata dal pannello -> è utilizzabile da più pannelli potenzialmente;

**Svantaggi**:
* classe esterna -> non può accedere a campi/metodi privati -> il pannello deve essere dotato di getter / setter ulteriori;