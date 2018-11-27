package commandes.admin;

import commandes.Commande;
import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class AdminLogoff implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 1)
			return false;
		
		if (sender.isAdmin()) {
			sender.setAdmin(false);
			sender.showMessage(Affichage.green + "Vous n'êtes plus administrateur." + Affichage.reset);
		}
			
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /adminlogoff";
	}

	@Override
	public String getDescription() {
		return "se déconnecter du mode administrateur";
	}

}
