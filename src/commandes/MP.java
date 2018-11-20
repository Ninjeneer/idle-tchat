package commandes;

import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.TchatServer;

public class MP implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length <= 2)
			return false;
			
		
		for (GerantDeClient target : ts.getClientList()) {
			if (target.getPseudo().equals(args[1])) {
				
				String messageFinal = "";
				for (int i = 2; i < args.length; i++)
					messageFinal += args[i] + " ";
				
				target.showMessage(Affichage.italic + " ~ " + sender.getPseudo() + " vous a envoyé un MP : " + messageFinal + Affichage.reset);
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

	@Override
	public boolean isDisplayable() {
		return true;
	}

}
