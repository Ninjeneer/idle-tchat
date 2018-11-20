package commandes.admin;

import commandes.Commande;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class GetInfo implements Commande{

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2)
			return false;
		
		if (sender.isAdmin()) {
			for (GerantDeClient cible : ts.getClientList())
				if (args[1].equals(cible.getPseudo())) {
					sender.showMessage(Affichage.gras + "Informations sur " + cible.getPseudo() + " :");
					sender.showMessage("\t IP : " + cible.getSocket().getInetAddress());
					sender.showMessage("\t Hostname : " + cible.getSocket().getInetAddress().getHostName());
					
					return true;
				}
					
		} else {
			return false;
		}
		
		return false;
	}

	@Override
	public boolean estAffichable() {
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /getinfo Pseudo";
	}

	@Override
	public String getDescription() {
		return "affiche les informations du client";
	}

}
