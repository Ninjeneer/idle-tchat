package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Mute implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (args.length != 2)
			return false;
		
		if (sender.isAdmin()) {
			for (GerantDeClient cible : ts.getClientList())
				if (args[1].equals(cible.getPseudo())) {
					cible.showMessage(Affichage.rouge + "Vous avez été mute par un administrateur" + Affichage.reset);
					ts.sendNotification(sender, Affichage.rouge + cible.getPseudo() + " a été mute par un administrateur" + Affichage.reset);
					sender.showMessage(Affichage.rouge + "Vous avez mute " + cible.getPseudo() + Affichage.reset );
					cible.setMuted(true);
					
					
					
					return true;
				}
					
		} else {
			return false;
		}
		
		return false;
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
	public boolean estAffichable() {
		return false;
	}

}
