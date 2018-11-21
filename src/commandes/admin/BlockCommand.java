package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class BlockCommand implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2)
			return false;
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		
		for (GerantDeClient target : ts.getClientList())
			if (target.getPseudo().equals(args[1])) {
				target.setCommandAllowed(false);
				sender.showMessage(Affichage.red + "Vous avez bloqué les commandes de " + target.getPseudo() + Affichage.reset);;
				target.showMessage(Affichage.red + "Un administrateur a bloqué vos commandes !" + Affichage.reset);
				
				return true;
			}
		
		
		// aucun client trouvé
		sender.showMessage(Affichage.red + "ERREUR : ce client n'existe pas" + Affichage.reset);
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /blockcommand Pseudo";
	}

	@Override
	public String getDescription() {
		return "bloque les commandes d'un client";
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

}
