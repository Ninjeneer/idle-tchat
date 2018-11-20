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
		
		if (args[1].equals("password")) {
			sender.setAdmin(true);
			sender.showMessage(Affichage.vert + "Vous êtes désormais administrateur. Faites /adminlogoff pour vous déconnecter." + Affichage.reset);
		}
			
		return true;
	}

	@Override
	public boolean estAffichable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /adminlogin password";
	}

	@Override
	public String getDescription() {
		return "se connecte en tant qu'administrateur";
	}

}
