package commandes.admin;

import commandes.Commande;
import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class KickAll implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		
		ts.sendNotification(sender, Affichage.red + "Un administrateur a kické tout le monde !" + Affichage.reset);
		
		int nbKick = ts.getClientList().size();
		for (int i = 0; i < nbKick; i++) {
			if (!ts.getClientList().get(i).isAdmin()) {
				ts.getClientList().get(i).showMessage(Affichage.red + "Vous avez été kick par un administrateur" + Affichage.reset);
				ts.getClientList().get(i).disconnect();
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
		return "USAGE : /kickall";
	}

	@Override
	public String getDescription() {
		return "kick tous les clients";
	}

}
