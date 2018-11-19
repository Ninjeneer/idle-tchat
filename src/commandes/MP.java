package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;

public class MP implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length < 2) {
			System.out.println("err1");
			return false;
		}
			
		
		for (GerantDeClient gdc : ts.getClientList()) {
			if (gdc.getPseudo().equals(args[1])) {
				String[] message = new String[args.length-2];
				for (int i = 2; i < args.length; i++)
					message[i-2] = args[i];
				
				String messageFinal = "";
				for (String mot : message)
					messageFinal += mot + " ";
				
				gdc.showMessage("\033[3m ~ " + sender.getPseudo() + " vous a envoyé un MP : " + messageFinal + "\033[0m");
				System.out.println("envoyé");
			}
		}
		
		
		return true;
			
				
	}

}
