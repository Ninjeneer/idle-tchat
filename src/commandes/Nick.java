package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Nick implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2)
			return false;
		
		if (args[1].length() > 20)
			return false;
		else {
			ts.sendNotification(sender, Affichage.gris + sender.getPseudo() + " a modifié son pseudo pour : " + args[1] + Affichage.reset);
			sender.setPseudo(args[1]);
			sender.showMessage(Affichage.gras + Affichage.gris + "Vous avez changé de pseudo !" + Affichage.reset);
			return true;
		}
	}

	@Override
	public String getError() {
		return "USAGE : /nick NouveauPseudo";
	}

	@Override
	public String getDescription() {
		return "permet de changer de pseudonyme";
	}

	@Override
	public boolean estAffichable() {
		return true;
	}

}
