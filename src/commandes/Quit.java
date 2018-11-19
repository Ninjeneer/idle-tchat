package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;

public class Quit implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.deconnecter();
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /quit";
	}

	@Override
	public String getDescription() {
		return "permet de se déconnecter";
	}

	@Override
	public boolean estAffichable() {
		return true;
	}

}
