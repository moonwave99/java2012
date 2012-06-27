# Java Final Test 27/06/2012

## Obiettivo

Scrivere un prototipo di videogioco sparatutto a scorrimento orizzontale, che presenti i tre stati seguenti:

* **stato iniziale:** schermata nera con scritto "Nuova partita?";
* **stato di gioco:** sono presenti una navicella controllata dal giocatore, ed un nemico in movimento;
* **stato di pausa:** tutti i movimenti e le interazioni vengono fermate, ed appare un layer semitrasparente con scritto "Paused".

Durante lo **stato di gioco** sono previste le seguenti interazioni:

* **freccia su:** la navicella acquista accelerazione verticale verso l'alto;
* **freccia giù:** la navicella acquista accelerazione verticale verso il basso;
* **barra spaziatrice:** la navicella spara un colpo in direzione parallela all'asse x.

Inoltre:

* il nemico si muove di moto armonico uniforme lungo l'asse y [fa su e giù];
* il nemico cresce continuamente fino ad una soglia superiore pari ai 3/2 delle dimensioni iniziali con cui è stato costruito;
* se un colpo raggiunge il nemico, le sue dimensioni diminuiscono leggermente [pur continuando a crescere!].

In alto è presente un **menu** con due elementi:

* **menuitem New Game:** inizia una nuova partita;
* **menuitem Pause:** ferma il gioco corrente, oppure lo riprende se era già stato fermato.

***

**Diego Caponera - Programmazione in Java e Gestione della Grafica**