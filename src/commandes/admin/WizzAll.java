package commandes.admin;

import commandes.Commande;
import commandes.Wizz;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class WizzAll implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		for (GerantDeClient cible : ts.getClientList())
			new Wizz().onCommand(ts, sender, new String[] {cible.getPseudo()});
		return true;
	}

	@Override
	public boolean estAffichable() {
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
