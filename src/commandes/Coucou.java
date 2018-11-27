package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class Coucou implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.showMessage(Affichage.bold + "Vous faites coucou à tout le monde :)" + Affichage.reset);
		ts.sendNotification(sender, "\033[1m" + sender.getPseudo() + " vous fait à tous un grand coucou !\033[0m");
		return true;
	}

	@Override
	public String getError() {
		return "";
	}

	@Override
	public String getDescription() {
		return "faites coucou à tout le monde !";
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

	
}
