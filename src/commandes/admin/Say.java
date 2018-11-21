package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Say implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length < 2)
			return false;
			
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande");
			return true;
		}
		
				
		String messageFinal = "";
		for (int i = 1; i < args.length; i++)
			messageFinal += args[i] + " ";
		
		ts.sendNotification(sender, Affichage.bold + messageFinal + Affichage.reset);
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /say Message";
	}

	@Override
	public String getDescription() {
		return "parle dans le tchat en tant qu'admin";
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

}
