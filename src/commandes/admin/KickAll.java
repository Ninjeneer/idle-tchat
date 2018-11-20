package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class KickAll implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		ts.sendNotification(sender, Affichage.rouge + "Un administrateur a kické tout le monde !" + Affichage.reset);
		for (GerantDeClient cible : ts.getClientList()) {
			if (!cible.isAdmin()) {
				cible.deconnecter();
			}
		}
			
		
		
		return true;
	}

	@Override
	public boolean estAffichable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /kickall";
	}

	@Override
	public String getDescription() {
		return "kick tous les clients";
	}

}
