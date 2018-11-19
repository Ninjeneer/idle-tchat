package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class MP implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length <= 2)
			return false;
			
		
		for (GerantDeClient cible : ts.getClientList()) {
			if (cible.getPseudo().equals(args[1])) {
				String[] ensembleMot = new String[args.length-2];
				for (int i = 2; i < args.length; i++)
					ensembleMot[i-2] = args[i];
				
				String messageFinal = "";
				for (String mot : ensembleMot)
					messageFinal += mot + " ";
				
				cible.showMessage(Affichage.italic + " ~ " + sender.getPseudo() + " vous a envoyé un MP : " + messageFinal + Affichage.reset);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String getError() {
		return "USAGE : /mp PseudoCible Message";
	}

	@Override
	public String getDescription() {
		return "envoie un message privé au destinataire";
	}

}
