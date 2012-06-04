## Applicazione di Filtri Convolutivi su immagini

Finora abbiamo gestito immagini esclusivamente tramite classe ```Image```, oppportuna per la visualizzazione di contenuti grafici ma non per la manipolazione degli stessi. Vedremo ora alcune applicazioni della classe ```BufferedImage```, adatta alla gestione del contenuto numerico di immagini all'interno del proprio spazio di colore - pagando un po' di dazio in termini computazionali, cioè di tempo e memoria.

La logica relativa a ```BufferedImage``` è la seguente:

	// Prelevo una normale Image:
	Image tempImage = Toolkit.getDefaultToolkit().createImage( 'some/path/file.jpg' );

	// Creo una BufferedImage vuota con dimensioni pari a quella dell'Image caricata da file
    BufferedImage loadedImage = new BufferedImage(
		tempImage.getWidth(this),
		tempImage.getHeight(this),
		BufferedImage.TYPE_INT_RGB
	);

	// Creo un oggetto Graphics2D per poter disegnare effettivamente all'interno della BufferedImage
	Graphics2D bufLoad = loadedImage.createGraphics();
	bufLoad.drawImage(tempImage, 0, 0, this);

In ordine:

* Ho caricato il contenuto di un file all'interno di un oggetto ```Image```;
* Ho creato un oggetto ```BufferedImage``` di dimensioni spaziali pari a quelle del file;
* Ho *riversato* i contenuti del primo all'interno del secondo tramite già noto metodo ```drawImage``` della classe ```Graphics2D``` - che non è lo stesso del metodo ```paint()``` eventuale, occhio!

Ora sono pronto per rappresentare su schermo tale immagine:

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		g2.drawImage(
			loadedImage,
			0,
			0,
			null
		);

	}

Finora non sembrano esserci guadagni sostanziali rispetto all'uso di ```Image``` - aggiungiamo anzi un paio di tediosi passaggi. Passiamo ora ad applicare un *filtro* all'immagine, azione possibile grazie a ```BufferedImage```.
Brevemente, per operazione di **filtro** si intende la manipolazione del contenuto frequenziale di un segnale [suono, immagine] secondo una regola opportuna. L'equalizzatore del vostro impianto stereo è un filtro per il suono in ingresso, i vostri occhiali da sole agiscono da filtro sulla luce, molti strumenti di Photoshop sono dichiaratamente filtri.

In questa sede useremo dei *filtri a convoluzione*, cioè faremo "camminare" una funzione dal supporto piccolo chiamata *nucleo* lungo tutta l'immagine e ne valuteremo gli effetti. Formalmente la convoluzione tra due segnali discreti **f[i]** e **g[i]** è così definita:

	(f * g)[i] = sum_j f[j] * g[j-i]

[Non preoccupatevi, vedrete con dettaglio maggiore nei corsi successivi.]

Ora Java applica tale formula in un contesto bidimensionale [un'immagine non è un suono!] utilizzando solitamente come nucleo una matrice 3x3 [rappresentata in un vettore di 3x3 = 9 componenti]:

	// Inizializzo un vettore di 9 componenti rappresentante la matrice
	float[]	filter = new float[]{
			-1.0f, -1.0f, -1.0f,
			-1.0f, 9.0f, -1.0f,
			-1.0f, -1.0f, -1.0f
		};

	// Inizializzo il Kernel con il filtro definito sopra
	Kernel kernel = new Kernel(3, 3, filter);

	// Definisco un'operazione di convoluzione con il kernel appena creato
    ConvolveOp convolve = new ConvolveOp(
		kernel,
		ConvolveOp.EDGE_NO_OP,
        null
	);

	// Applico la convoluzione sull'immagine di partenza, con destinazione un'altra BufferedImage
    convolve.filter(startingImage, destinationImage);

Abbiamo appena applicato una *unsharp mask* sull'immagine, evidenziandone i dettagli. Con nuclei opportuni è possibile applicare sfumature, *edge detection* e molte altre operazioni - cercate "convolution kernel Graphics2D" e troverete materiale interessante.

---

Il viaggio insieme è [quasi] terminato, ma non il vostro cammino da soli: spero di avervi trasmetto un minimo di interesse per la disciplina dello sviluppo e mi auguro che possa rappresentare fonte di scoperta e di soddisfazione per molti di voi.

Resto a vostra disposizione per qualsiasi richiesta e vi ringrazio per avermi sopportato - have a nice life!

*D.*