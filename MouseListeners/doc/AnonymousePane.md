## Ancora su eventi: ascoltatori annidati anonimi

Ultimo ripasso sugli ascoltatori: vediamo come gestire il solito drag della pallina, stavolta tramite *anonymous inner classes*:

Vi ricordo:
* **anonymous** : alla classe definita non viene dato nome esplicito;
* **inner** : la classe è definita *all'interno* di un'altra classe.

Guardiamo il costruttore del pannello:

	public AnonymousPane()
	{

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
	
		addMouseListener(new MouseListener(){
	
			public void mousePressed(MouseEvent me){ ... }	
			public void mouseClicked(MouseEvent me){}
			public void mouseReleased(MouseEvent me){ ... }
			public void mouseEntered(MouseEvent me){}
			public void mouseExited(MouseEvent me){}
		
		});
	
		addMouseMotionListener(new MouseMotionListener(){
	
			public void mouseMoved(MouseEvent me){}
			public void mouseDragged(MouseEvent me){ ... }						
		
		});

	}

Mentre in passato avevamo definito il pannello stesso come ```MouseListener```, implementando tale interfaccia, stavolta creiamo l'ascoltatore in maniera anonima secondo la sintassi indicata.

**Vantaggi**:
* sintassi più concisa;
* essendo una classe interna, accede a metodi e campi del pannello senza problemi [es. ```repaint()```].

**Svantaggi**:
* si perde in riusabilità - se voglio estendere la classe ```AnonymousPane```, non posso fare *overload* dei metodi relativi al mouse perché non sono esposti, bensì propri della classe annidata anonima.