# IDLETCHAT

**IdleTchat** est un logiciel de **tchat en ligne** réalisé en Java dans le cadre d'un projet universitaire.

## Démarrage du serveur
	java server.TchatServer [portNumber] //default port : 8003
	
## Démarrage du client

### Client en mode console
Pour exécuter le client en mode CUI : `java client.Client`
* Entrez votre pseudo
* Entrez l'adresse du serveur auquel vous souhaitez accéder
* Enjoy :)

### Client en mode graphique
Pour exécuter le client en mode GUI : `java client.controler.Controler`

* Menu "Client" -> "Connexion"
* Entrez l'adresse du serveur auquel vous souhaitez accéder
* Entrez votre pseudo
* Enjoy :)
	
	
## Utilisation
De nombreuses commandes existent. Pour les lister, faites /help.
	
		
### Administration
Il existe dans IdleTchat un système d'administration. Pour devenir administrateur, entrez cette commande : /adminlogin idle8003
Être administrateur vous permet d'accéder à toutes les commandes listées dans le /adminhelp
	

## Déconnexion
Pour vous déconnecter, vous pouvez utiliser la commande /quit (fonctionne en CUI et GUI) ou utiliser le menu Client > Déconnexion (fonctionne uniquement en GUI) 
	
	
	
	
## Modification du code
	
Il est facilement faisable de rajouter des commandes à votre guise. Il suffit de créer une classe implémentant l'interface Commande, et d'ajouter celle-ci dans 
TchatServeur à l'aide la méthode 
	addCommande(String nomCommande, Commande cmd);
	
	
