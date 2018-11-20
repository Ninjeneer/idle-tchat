package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Wizz implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2) {
			return false;
		}
			
		
		for (GerantDeClient cible : ts.getClientList()) {
			if (cible.getPseudo().equals(args[1])) {
				String alea = "";
				for(int i = 0; i < 3000; i++)
					alea += Affichage.randomColor() + (char) ('A' + (int) (Math.random() * ('Z'-'A')));
				
				sender.showMessage(Affichage.gras + "Vous avez envoyé un Wizz à " + cible.getPseudo() + Affichage.reset);
				cible.showMessage(Affichage.gras + alea + Affichage.reset);
				cible.showMessage(Affichage.gras + sender.getPseudo() + " vous a envoyé un Wizz !" + Affichage.reset);
				
				return true;
			}
		}
		

		return false;		
	}

	@Override
	public String getError() {
		return "USAGE : /wizz PseudoCible";
	}

	@Override
	public String getDescription() {
		return "envoie un Wizz à la personne ciblée";
	}

	@Override
	public boolean estAffichable() {
		return true;
	}

}
