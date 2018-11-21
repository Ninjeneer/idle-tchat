package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Who implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.showMessage(Affichage.bold + "Liste des clients connectés (" + ts.getClientList().size() + "): " + Affichage.reset);
		for (GerantDeClient client : ts.getClientList()) {
			String connecte = "\t -> " + client.getCouleur() +  client.getPseudo() + Affichage.red;
			
			if (client.isMuted())
				sender.showMessage(connecte + " (muted)" + Affichage.reset);
			else
				sender.showMessage(connecte + Affichage.reset);
		}
			
		
		return true;
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

	@Override
	public String getError() {
		return "USAGE : /who";
	}

	@Override
	public String getDescription() {
		return "affiche la liste des clients connectés";
	}

}
