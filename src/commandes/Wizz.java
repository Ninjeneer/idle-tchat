package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;

public class Wizz implements Commande {

	@Override
	public boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args) {
		if (args.length != 2) {
			System.out.println("err1");
			return false;
		}
			
		
		for (GerantDeClient gdc : ts.getClientList()) {
			if (gdc.getPseudo().equals(args[1])) {
				String alea = "";
				for(int i = 0; i < 2000; i++)
					alea += (char) ('A' + (int) (Math.random() * 'Z'));
				gdc.showMessage("\033[1m" + alea + "\033[0m");
				
			}
		}
		
		
		
		return true;
			
				
	}

}
