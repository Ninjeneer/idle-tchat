package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;
import utils.Affichage;

public class Who implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		
		String connecte = Affichage.bold + "Liste des clients connectés (" + ts.getClientList().size() + "): " + Affichage.reset;
		
		for (GerantDeClient client : ts.getClientList()) {
			connecte += "\n\t -> " + client.getCouleur() +  client.getPseudo();
			
			if (client.isMuted())
				connecte += Affichage.red + " (muted)";

			connecte += Affichage.reset;
			
		}
		
		sender.showMessage(connecte);
			
		
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
