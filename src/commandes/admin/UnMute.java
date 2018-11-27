package commandes.admin;

import commandes.Commande;
import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class UnMute implements Commande{

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
				target.showMessage(Affichage.bold + Affichage.red + "Vous avez été unmute par " + sender.getPseudo() + Affichage.reset);
				ts.sendNotification(sender, Affichage.bold + Affichage.red + target.getPseudo() + " a été unmute par " + sender.getPseudo() + Affichage.reset);
				sender.showMessage(Affichage.red + "Vous avez unmute " + target.getPseudo() + Affichage.reset );
				target.setMuted(false);
				
				return true;
			}
		
		//aucun client trouvé
		sender.showMessage(Affichage.red + "ERREUR : ce client n'existe pas" + Affichage.reset);
		return true;

	}

	@Override
	public String getError() {
		return "USAGE : /unmute Pseudo";
	}

	@Override
	public String getDescription() {
		return "Unmute un client";
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

}
