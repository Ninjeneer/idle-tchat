package commandes.admin;

import commandes.Commande;
import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class Mute implements Commande{

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
				target.showMessage(Affichage.red + "Vous avez été mute par un administrateur" + Affichage.reset);
				ts.sendNotification(sender, Affichage.red + target.getPseudo() + " a été mute par un administrateur" + Affichage.reset);
				sender.showMessage(Affichage.red + "Vous avez mute " + target.getPseudo() + Affichage.reset );
				target.setMuted(true);
				
				
				
				return true;
			}
					
		
		// aucun client trouvé
		sender.showMessage(Affichage.red + "ERREUR : ce client n'existe pas" + Affichage.reset);
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /mute Pseudo";
	}

	@Override
	public String getDescription() {
		return "Mute un client";
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

}
