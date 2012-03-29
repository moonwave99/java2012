## Disegno e trascinamento di forme: implementazione di un interfaccia comune

Finora abbiamo visto come disegnare una forma su un ```JPanel```: all'interno del metodo ```paint()``` possiamo accedere al _pennello_ [istanza di ```Graphics2D```], e quindi richiamarne i metodi utili alla rappresentazione grafica.

A breve vorremo disegnare [e trascinare] più forme in un pannello, avremo quindi bisogno di un modo trasversale per dire al pennello di disegnare le varie forme create: ci viene in soccorso la definizione di un'**interfaccia** idonea, che indichi i metodi necessari affinché un oggetto sia rappresentabile e trascinabile:

    interface DraggableShape
    {
		public boolean contains(Point2D p);
		public void draw(Graphics2D g2);
		public void move(Point p);
		public void setPinPoint(Point p);
		public void releasePinPoint();	
	}

Come abbiamo notato in aula, all'interno di ```ShapePane``` vengono invocati esclusivamente i metodi dell'interfaccia - questo perché il campo ```shape``` è stato dichiarato di tipo ```DraggableShape``` e non ```Circle```/```ColoredCircle``` - il programma sa di avere a che fare con qualcosa di *disegnabile*, ma non sa di preciso cosa sia [e non gli interessa in fondo].

Altro spunto: nell'interfaccia è richiesto il metodo ```contains()```, ed il programma gira sebbene tale metodo non sia esplicitamente stato implementato in nessuna porzione del codice fornito: questo perché ```Ellipse2D``` è già in possesso di tale metodo, quindi i requisiti di implementazione sono soddisfatti!

Semplici sviluppi ulteriori che vi invito a fare:

* classi Square e ColoredSquare che disegnino un **quadrato**;
* classe Triangle che disegni un **triangolo equilatero** - pensate a quanto visto nella parte finale della lezione!

Buon lavoro,

*D.*