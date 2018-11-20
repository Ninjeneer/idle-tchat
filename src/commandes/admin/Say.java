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
			
		
				
		String messageFinal = "";
		for (int i = 1; i < args.length; i++)
			messageFinal += args[i] + " ";
		
		ts.sendNotification(sender, Affichage.gras + messageFinal + Affichage.reset);
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
	public boolean estAffichable() {
		return false;
	}

}
