package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Wizz implements Commande {
	
	public static String getWizz() {
		String alea = "";
		for(int i = 0; i < 3000; i++)
			alea += Affichage.randomColor() + (char) ('A' + (int) (Math.random() * ('Z'-'A')));
		
		return alea;
	}

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2) {
			return false;
		}
			
		
		for (GerantDeClient target : ts.getClientList()) {
			if (target.getPseudo().equals(args[1])) {
				sender.showMessage(Affichage.bold + "Vous avez envoyé un Wizz à " + target.getPseudo() + Affichage.reset);
				target.showMessage(Affichage.bold + Wizz.getWizz() + Affichage.reset);
				target.showMessage(Affichage.bold + sender.getPseudo() + " vous a envoyé un Wizz !" + Affichage.reset);
				
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
	public boolean isDisplayable() {
		return true;
	}

}
