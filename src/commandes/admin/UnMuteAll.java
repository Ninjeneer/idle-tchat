package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class UnMuteAll implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande");
			return true;
		}
		

		for (GerantDeClient target : ts.getClientList())
			if (!target.isAdmin()) {
				target.setMuted(false);
				target.showMessage(Affichage.red + "Un administrateur vous a mute" + Affichage.reset);
			}
		
		ts.sendNotification(sender, Affichage.red + "Un administrateur a mute tous les clients" + Affichage.reset);
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /unmuteall";
	}

	@Override
	public String getDescription() {
		return "unmute tous les clients";
	}

}
