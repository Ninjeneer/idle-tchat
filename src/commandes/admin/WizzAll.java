package commandes.admin;

import commandes.Commande;
import commandes.Wizz;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class WizzAll implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		
		sender.showMessage(Affichage.bold + "Vous avez envoyé un Wizz à tout le monde" + Affichage.reset);
		for (GerantDeClient target : ts.getClientList()) {
			if (!target.isAdmin()) {
				target.showMessage(Affichage.bold + Wizz.getWizz() + Affichage.reset);
				target.showMessage(Affichage.bold + sender.getPseudo() + " vous avez reçu un Wizz !" + Affichage.reset);
			}
		}
			
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /wizzall";
	}

	@Override
	public String getDescription() {
		return "envoie un wizz à tout les clients";
	}

}
