package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class UnMute implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		if (args.length != 2)
			return false;
		
		if (sender.isAdmin()) {
			for (GerantDeClient cible : ts.getClientList())
				if (args[1].equals(cible.getPseudo())) {
					cible.showMessage(Affichage.gras + Affichage.rouge + "Vous avez été unmute par " + sender.getPseudo() + Affichage.reset);
					ts.sendNotification(sender, Affichage.gras + Affichage.rouge + cible.getPseudo() + " a été unmute par " + sender.getPseudo() + Affichage.reset);
					sender.showMessage(Affichage.rouge + "Vous avez unmute " + cible.getPseudo() + Affichage.reset );
					cible.setMuted(false);
					
					return true;
				}
					
		} else {
			return false;
		}
		
		return false;
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
	public boolean estAffichable() {
		return false;
	}

}
