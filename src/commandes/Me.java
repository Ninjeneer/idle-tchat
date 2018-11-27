package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class Me implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length < 2)
			return false;
		
		String messageFinal = "";
		
		for (int i = 1; i < args.length; i++)
			messageFinal += args[i] + " ";
		
		ts.sendNotification(sender, Affichage.italic + Affichage.grey + "* " + sender.getPseudo() + " " + messageFinal + Affichage.reset);
		sender.showMessage(Affichage.italic + Affichage.grey + "* " + sender.getPseudo() + " " + messageFinal + Affichage.reset);
			
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /me action";
	}

	@Override
	public String getDescription() {
		return "permet de faire une action";
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

}
