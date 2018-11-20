package commandes.admin;

import commandes.Commande;
import commandes.Wizz;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class WizzAll implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {

		for (GerantDeClient cible : ts.getClientList()) {
			if (!cible.isAdmin()) {
				sender.showMessage(Affichage.gras + "Vous avez envoyé un Wizz à tout le monde " + cible.getPseudo() + Affichage.reset);
				cible.showMessage(Affichage.gras + Wizz.getWizz() + Affichage.reset);
				cible.showMessage(Affichage.gras + sender.getPseudo() + " vous avez reçu un Wizz !" + Affichage.reset);
			}
		}
			
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
