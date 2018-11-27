package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class ChangerCouleur implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.setColor(Affichage.randomColor());
		sender.showMessage(sender.getCouleur() + "Vous avez changé de couleur ! :D" + Affichage.reset);
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /changercouleur";
	}

	@Override
	public String getDescription() {
		return "change la couleur de votre pseudo";
	}

}
