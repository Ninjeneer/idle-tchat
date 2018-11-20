package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Who implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.showMessage(Affichage.gras + "Liste des clients connectés (" + ts.getClientList().size() + "): " + Affichage.reset);
		for (GerantDeClient client : ts.getClientList())
			if (client.isMuted())
				sender.showMessage("\t -> " + client.getCouleur() +  client.getPseudo() + Affichage.rouge + "(muted)" + Affichage.reset);
			else
				sender.showMessage("\t -> " + client.getCouleur() +  client.getPseudo() + Affichage.reset);
		
		return true;
	}

	@Override
	public boolean estAffichable() {
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
