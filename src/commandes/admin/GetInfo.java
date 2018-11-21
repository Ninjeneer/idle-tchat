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
		
		if (!sender.isAdmin()) {
			sender.showMessage(Affichage.red + "ERREUR : vous ne pouvez pas accéder à cette commande" + Affichage.reset);
			return true;
		}
		

		for (GerantDeClient target : ts.getClientList())
			if (args[1].equals(target.getPseudo())) {
				sender.showMessage(Affichage.bold + "Informations sur " + target.getPseudo() + " :");
				sender.showMessage("\t IP : " + target.getSocket().getInetAddress());
				sender.showMessage("\t Hostname : " + target.getSocket().getInetAddress().getHostName());
				
				return true;
			}
					

		// aucun client trouvé
		sender.showMessage(Affichage.red + "ERREUR : ce client n'existe pas" + Affichage.reset);
		return true;
	}

	@Override
	public boolean isDisplayable() {
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
