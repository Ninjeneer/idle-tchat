package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class KickAll implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (sender.isAdmin()) {
			ts.sendNotification(sender, Affichage.rouge + "Un administrateur a kické tout le monde !" + Affichage.reset);
			System.out.println(ts.getClientList().size());
			for (int i = 0; i < ts.getClientList().size(); i++) {
				if (!ts.getClientList().get(i).isAdmin()) {
					ts.getClientList().get(i).deconnecter();
				}
			}
			
			return true;
		}
			

		return false;
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
