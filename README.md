# BikeRent
bikeRent Android application

Richiesta:
L'idea è quella di fare un noleggio itinerante delle E-Bike da offrire agli ospiti di un albergo, di una nave da crociera o di altre strutture
ricettive turistiche. Qualcosa di simile io l'ho visto a Firenze. L'idea dovrebbe funzionare così: il cliente dell'Hotel o lo stesso albergatore
attraverso una app prenota una ebike per un giro, sulla app gli compare di quanto sarà il tempo di attesa (stimato) ed un furgone arriva poi con 
la bici o le bici e le consegna. Al ritiro si concorda anche la riconsegna della bici. Naturalmente il tutto dovrebbe essere collegato ad una 
piattaforma web che permetta a noi, ma anche ai Clienti, di monitorare la disponiblità effettiva delle bici. Spero di essere stato chiaro, 
naturalemente mi serve sapere anche i tempi di realizzazione ed i costi.


Progettazione:
Applicazione wireless internet con un database sull'app per poter visualizzare le vecchie prenotazioni o quella attuale (Count-Down).
Per maggior sicurezza, dato bisogna offrire un servizio a piu livelli (cliente, autista, dirigente(?)), invece di gestire accessi diversi
con login diversi, possiamo sviluppare 2/3 app.

APP CLIENTE
Offre un servizio ai clienti i quali al primo accesso, dopo aver scaricato l'app, devono registrarsi con nome, cognome, numero di carta di identità
numero di carta di credito che possono inserire anche in un secondo momento (o magari può pagare in contanti all'autista), numero di cellulare.
Il login resterà permanente (quindi tipo la prima activity è mostrata solo se la flag registrato è = false). Dall'interno le voce del menù permettono
di modificare il numero di carta, spiega come funziona il servizio, permette di effettuare una richiesta andando a specificare il luogo in cui si vuole ricevere la bici
(tramite gps con posizione attuale, o riempiendo un modulo con cap, città via e civico). Al termine (o in fase di prenotazione) verrà mostrato 
all'utente il tempo richiesto affinchè il furgone lo raggiunga.

APP AUTISTA
Richiede un acceso attraverso una form di login, ad ogni autista corrisponde un furgone con un tot numero di bici. Tutti gli autisti possono visualizzare
le richieste fatte in ordine temporale, oppure su una mappa in modo che in base alla loro posizione possano scegliere quelle piu vicine e con più rapide da
effettuare (magari è possibile sfruttare delle api google che oltre alla distanza tengono conto anche del traffico).
Ogni richiesta è fornita di tutte le informazioni necessarie, nome, cognome, numero di cell, se paga in contanti o con carta.

APP DIRIGENTE
Il dirigente raccoglie tutte le informazioni sui clienti e il furgoncino che ha soddisfatto l' "ordine".


Activity:
Proprietario
	Activity
		Posizione 

Camionista
	Activity
		scheduler richieste (quantità, costo)
			posizione -> indicazioni (navigatore gmap)
Utente
	Activity
		registrazione nome, cogmone, telefono(conferma messaggio codice), email(conferma) [attivazione sicura in quanto il pagamento potrebbe avvenire al ritiro] // Alla prima esecuzione (flag = 1 una volta completata?)
		Richiesta [posizione attuale(GPS), manuale, numero bici, data e orario, durata, inserimento dati pagamento (API), calcolo tempistica (se prenotazione a breve)]
		Visualizza stato richieste [tempo, posizione, numero prenotazione, costo, numero bici, durata, riconsegna]

More Features:
Vota l'app (si integra facilmente?)
Count Down richieste attive
Fitness Velocità attuale, velocità media, calorie consumate, km percorsi.
Allert Ti stai allontanando troppo dal luogo di consegna (avviso sul db -> Camionista/Proprietario)


Link utili:
https://msdn.microsoft.com/library/dn771552.aspx#NET
# Test
# Test
