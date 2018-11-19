package commandes;

import java.util.Map.Entry;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Help implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		sender.showMessage(Affichage.gras + "Liste des commandes : " + Affichage.reset);
		for (Entry<String, Commande> commande : ts.getCommandeList().entrySet())
			sender.showMessage("\t" + commande.getKey() + " - " + commande.getValue().getDescription()); 
			
			
		return true;
	}

	@Override
	public String getError() {
		return "";
	}

	@Override
	public String getDescription() {
		return "affiche la liste des commandes";
	}

}
