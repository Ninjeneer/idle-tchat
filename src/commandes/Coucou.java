package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;

public class Coucou implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.showMessage("\033[1m [Vous faites coucou à tout le monde]\033[0m");
		ts.sendNotification(sender, "\033[1m [" + sender.getPseudo() + " vous fait à tous un grand coucou !]\033[0m");
		return true;
	}

	
}
