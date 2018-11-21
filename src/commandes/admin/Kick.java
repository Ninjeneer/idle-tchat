package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Kick implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (args.length != 2)
			return false;
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		

		for (GerantDeClient target : ts.getClientList())
			if (args[1].equals(target.getPseudo())) {
				target.showMessage(Affichage.bold + Affichage.red + "Vous avez été kick par un administrateur" + Affichage.reset);
				ts.sendNotification(sender, Affichage.bold + Affichage.red + target.getPseudo() + " a été kické par un administrateur" + Affichage.reset);
				sender.showMessage(Affichage.red + "Vous avez kick " + target.getPseudo() + Affichage.reset );
				target.disconnect();
				
				return true;
			}
					
		
		// aucun client trouvé
		sender.showMessage(Affichage.red + "ERREUR : ce client n'existe pas" + Affichage.reset);
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /kick Pseudo";
	}

	@Override
	public String getDescription() {
		return "Kick un client";
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

}
