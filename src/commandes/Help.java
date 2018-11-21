package commandes;

import java.util.Map.Entry;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class Help implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length == 1) {
			sender.showMessage(Affichage.bold + "Liste des commandes : " + Affichage.reset);
			for (Entry<String, Commande> commande : ts.getCommandeList().entrySet())
				if (commande.getValue().isDisplayable())
					sender.showMessage(String.format("%15s", commande.getKey()) + " - " + String.format("%-50s", commande.getValue().getDescription()));
			
			return true;
		}
		
		
		if (args.length == 2) {
			for (Entry<String, Commande> commande : ts.getCommandeList().entrySet())
				if (commande.getKey().equals(args[1]) && commande.getValue().isDisplayable()) {
					sender.showMessage(Affichage.bold + "Aide concernant la commande /" + commande.getKey() + " :" + Affichage.reset);
					sender.showMessage("\t" + commande.getValue().getDescription());
					sender.showMessage("\t" + commande.getValue().getError());
					
					return true;
				}
		}
		 
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /help [commande]";
	}

	@Override
	public String getDescription() {
		return "affiche la liste des commandes";
	}

	@Override
	public boolean isDisplayable() {
		return true;
	}

}
