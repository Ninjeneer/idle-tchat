package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class MuteAll implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		
		for (GerantDeClient target : ts.getClientList())
			if (!target.isAdmin()) {
				target.setMuted(true);
				target.showMessage(Affichage.red + "Un administrateur vous a mute" + Affichage.reset);
			}
		
		ts.sendNotification(sender, Affichage.red + "Un administrateur a mute tous les clients" + Affichage.reset);
		return true;

	}

	@Override
	public boolean isDisplayable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
