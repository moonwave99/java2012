## Gestione dei processi: classe Thread ed interfaccia Runnable

In Java [e nella programmazione in generale], è ragionevole voler gestire i processi in maniera ordinata in funzione nel tempo, sia nel caso essi siano singoli o paralleli. Senza entrare troppo nel dettaglio, possiamo definire un **processo** come l'esecuzione di un programma o di una porzione di esso che abbia accesso a risorse del sistema. Un programma semplice può essere costituito da un singolo processo, mentre alcuni più complessi possono richiederne di ulteriori secondo necessità - pensate ad un programma che riproduca contenuti multimediali in streaming: è bene che il processo di acquisizione dei dati sia separato e parallelo rispetto a quello di visualizzazione.

In Java i processi sono gestiti dalla classe ```Thread```: per le necessità che incontreremo, il flusso di lavoro sarà pressoché sempre il seguente:

*	La classe principale del programma [o quantomeno quella deputata al disegno] implementi l'interfaccia ```Runnable```;
*	Essa è costituita dal singolo metodo ```public void run()```, lo si implementi vuoto per ora - esso conterrà la logica del programma che verrà eseguita *di continuo*, per simulare un movimento in funzione del tempo ad esempio;
*	Si doti la classe principale di un'istanza [protetta] della classe ```Thread```, che per convenzione chiameremo ```th```: essa riceve un oggetto di tipo ```Runnable``` nel costruttore, che ovviamente sarà la classe principale stessa [```this.th = new Thread(this)```];
*	Si doti la classe principale dei seguenti metodi: ```start()```, ```stop()```, ```isRunning()```, le cui implementazioni verranno ora esaminate.

Il processo "coincide" con il metodo ```run()```: le istruzioni in esso contenute vengono ripetute finché il processo vive, quantomeno nel nostro caso d'uso. Tale metodo non viene invocato in maniera diretta in alcuna parte del codice, la sua esecuzione viene quindi delegata al ```Thread```: guardando l'implementazione consigliata del metodo ```start()``` del **pannello** sarà tutto chiaro:

	public void start()
	{

		if(!isRunning()){

			th = new Thread(this);
			th.start();

		}

	}

Dove ```isRunning()``` è definito per convenzione nel modo seguente:

	public boolean isRunning()
	{

		return th != null;

	}

Il metodo ```stop()``` è ulteriormente semplice:

	public void stop()
	{

		th = null;

	}

Montando i pezzi:

* il metodo ```start()``` del pannello crea il ```Thread``` e lo fa partire [chiamando e gestendo il metodo ```run()``` del pannello];
* il metodo ```isRunning()``` controlla se il ```Thread``` è vivo;
* il metodo	```stop()``` "distrugge" il ```Thread```.

Venendo al metodo ```run()```, eccone l'implementazione:

	public void run()
	{

		while(isRunning())
		{

			// istruzioni da ripetere

			try{

				Thread.sleep(40);

			}catch(InterruptedException e){

				e.printStackTrace();

			}

		}

	}

Finché il ```Thread``` è vivo, vengono eseguite a ripetizione le istruzioni del metodo ```run()```. Ogni quanto vengono ripetute? Per gestire in maniera verosimile il flusso del tempo, viene invocato il metodo [statico] ```sleep(int millisecs)``` della classe ```Thread```, che blocca il processo corrente per il numero di millisecondi indicato: fermare il processo ogni 40 millisecondi implica che venga ripetuto 1000 / 40 = 25 volte per secondo [25 Hertz!], che può essere un *framerate* discreto per adesso - a voi libertà di adattare questi parametri in maniera sensata. Ovviamente questo non tiene conto dell'esecuzione delle istruzioni ripetute: ogni iterazione dura quindi ```tempo operazioni + tempo sleep > tempo sleep```, e la gestione della sincronizzazione precisa dei processi è un tema sufficientemente interessante e complesso, che non verrà trattato in questo corso purtroppo - fidatevi per ora ^^.

L'implementazione di questi 4 metodi [uno proprio dell'interfaccia, gli altri tre suggeriti] è sufficiente per coprire le nostre esigenze in tema di ```Thread```.

Il programma proposto è il movimento di moto circolare uniforme di un cerchio lungo una circonferenza: provate a modificare il programma cambiando la legge oraria del cerchio con [altre traiettorie](http://it.wikipedia.org/wiki/Curva_a_farfalla).

Buon lavoro,

*D.*