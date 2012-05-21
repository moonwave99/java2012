## Gestione delle Immagini: caricamento di JPEG/PNG in un JPanel

Finora abbiamo rappresentato forme astratte generate tramite primitive di ```Graphics2D``` quali rettangoli, ellissi, linee - vediamo ora come caricare contenuti di tipo *raster* da disco.

Vi saranno noti i formati di file quali **JPEG** o **PNG**, e ne conoscerete per sommi capi elementi comuni e differenze; li riassiumiamo in breve per amor d'esempio:

* **JPEG** è un formato *a perdita d'informazione*, che quindi priva il file originale in maniera irreversibile di parte dell'informazione in essa contenuto;

* Tale informazione è però prescindibile in quanto quasi non percepita dall'occhio umano - ne beneficia la dimensione del file codificato;

* **PNG** è un formato *lossless*, non induce perdita di informazione e non garantisce al contempo compressione particolarmente vantaggiosa;

* La sua utilità principale è quella di poter salvare informazioni sul canale di trasparenza [*alpha*], rendendolo particolarmente adatto alla costruzione di interfacce in quanto riusabile su più sfondi.

Di passo in questo programma vedremo in maniera assasi stringata l'utilizzo della terna ```JMenuBar```/```JMenu```/```JMenuItem```, nonché l'utilizzo di ```AbstractAction```.

---

Lo scopo del programma è quello di rappresentare un'immagine [salvata su disco] su schermo: sarà necessario quindi sfruttare qualche metodo della classe ```Graphics2D``` da richiamare all'interno del ```paint()``` come è intuibile. Introduciamo prima la classe ```Image```, che ci fornisce un riferimento basico ad una risorsa grafica a partire da un indirizzo.

In questo esempio verrà così costruita:

	File f = new File("path/to/immagine.jpg");
	Image img = Toolkit.getDefaultToolkit().createImage(file.getAbsolutePath());

Bene, ```img``` è un'```Image``` pronta ad essere rappresentata nel ```paint()```:

	public void paint(Graphics g)
	{

		...

		g2.drawImage(
			img,
			0,
			0,
			null
		);

	}

Gestione delle eccezioni a parte, questo è quanto. Nel momento in cui dovremmo gestire un numero sensibile di risorse, o qualora a qualcuna di esse si chiederà accesso remoto, è bene sincerarsi che esse siano tutte caricate prima di procedere: ci viene in soccorso la classe ```MediaTracker```, che si preoccupa di bloccare il processo finché tali compiti non sono stati svolti.

	(all'interno del pannello => 'this' è esso stesso quindi)

	MediaTracker tracker = new MediaTracker(this);

	tracker.addImage(img, 0);

	try{

		tracker.waitForAll();

	}catch(InterruptedException e){

		e.printStackTrace();

	}

A questo punto siete certi che tutto il vostro materiale sia pronto, o avrete gestito gli eventuali problemi. Ora che sapete caricare un'immagine, passiamo al setup del resto - ci serve un'azione per l'apertura che sia invocabile da più contesti, dotiamo quindi il pannello di un metodo pubblico ```openImage(File imgFile)```:

	public void openImage(File imgFile)
	{

		//image è già un campo d'istanza
		image = Toolkit.getDefaultToolkit().createImage(imgFile.getAbsolutePath());

		// aggiungo l'immagine al tracker [preventivamente inizializzato come campo del pannello]
		tracker.addImage(image, 0);

		// attendo che il tracker carichi tutti i contenuti
		try{

			tracker.waitForAll();

		}catch(InterruptedException e){

			e.printStackTrace();

		}

		// adatto il frame all'immagine [metodo di ImagePane, non nativo!]
		updateCanvas();

		// aggiorno il titolo del frame
		f.setTitle(String.format("%s - %d x %d", imgFile.getName(), img.getWidth(null), img.getHeight(null) ));

		repaint();

	}

Ora posso invocare tale metodo all'interno della già citata ```AbstractAction```, il cui corpo è qui riportato:

	class OpenAction extends AbstractAction
	{

		// riferimento al pannello
		protected ImgPane pane;

		public OpenAction(ImgPane pane)
		{

			this.pane = pane;

		}

		public void actionPerformed(ActionEvent e)
		{

			// creo un JFileChooser per sfogliare le cartelle a partire dal percorso indicato
			JFileChooser fC = new JFileChooser(ImgPane.START_PATH);

			// se ho selezionato un file...
		    if(fC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

				//...provo ad aprirlo dal pannello:
				pane.openImage(fC.getSelectedFile());

		    }

		}

	}

```JFileChooser``` è un [verboso] componente per la scelta di file - vedrete che sarà possibile implementare dei filtri sulla selezione, funzione utile al momento di stabilire limiti in ingresso [esempio solo file grafici nel nostro caso].

È tutto pronto, bisogna solo creare il menu ed associarvi l'azione:

	protected JMenuBar createMenuBar()
	{

		// la gerarchia dei menu: JMenuBar > JMenu > JMenuItem
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");

		// all'elemento Open associo l'azione di apertura file
		openItem.addActionListener(new OpenAction(this));

		// a tale elemento associo uno shortcut da tastiera [CTRL+O]
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

		// aggiungo ogni elemento al proprio padre
		fileMenu.add(openItem);
		bar.add(fileMenu);

		//idem per l'altra azione
		JMenu imageMenu = new JMenu("Image");
		JMenuItem rotateItem = new JMenuItem("Rotate");

		rotateItem.addActionListener(new RotateAction(this));
		rotateItem.setAccelerator(
			KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK)
		);

		imageMenu.add(rotateItem);
		bar.add(imageMenu);

		return bar;

	}

E nel costruttore del pannello:

	public ImgPane()
	{

		...

		f = new JFrame("Img Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// non dimenticatevi di aggiungere la barra al frame!
		f.setJMenuBar(
			createMenuBar()
		);

		...

	}

Semplicemente, al frame viene associata una ```JMenuBar```, la quale può contenere dei ```JMenu``` quali *File*, *Edit*, *Help*, ognuno dei quali avrà uno o più ```JMenuItem```. Questi ultimi sono dei potenziali ```ActionListener```, e si può quindi dire loro di compiere un'azione quando vengono selezionati - in questo caso l'```AbstractAction``` prima definita.
Il metodo ```setAccelerator()``` serve a definire uno *shortcut* da tastiera per l'azione associata.

---

Gli scenari aperti sono molteplici, per ora provare a riscrivere il programma da zero come sempre, provando a fissare i concetti visti:

* apertura immagini tramite ```Image``` e ```Toolkit```;
* ```MediaTracker```;
* ```AbstractAction```;
* Rotazioni con ```AffineTransform```;
* ```JMenuBar``` e figli.

Buono studio,

*D.*