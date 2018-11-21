package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class AdminLogin implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2)
			return false;
		
		if (args[1].equals("idle8003")) {
			sender.setAdmin(true);
			sender.showMessage(Affichage.green + "Vous êtes désormais administrateur. Faites /adminlogoff pour vous déconnecter." + Affichage.reset);
		}
			
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return false;
	}

	@Override
	public String getError() {
		return "ERREUR : cette commande n'existe pas";
	}

	@Override
	public String getDescription() {
		return "se connecte en tant qu'administrateur";
	}

}
