package serveur;

import java.util.ArrayList;
import java.util.Collections;

public class RefreshClientList implements Runnable {

	private TchatServer ts;

	public RefreshClientList(TchatServer ts) {
		this.ts = ts;
	}

	@Override
	/**
	 * Envoi à tous les clients la liste des clients connectés toutes les secondes
	 */
	public void run() {
		
		
		
		while (true) {
			ArrayList<String> pseudoList = new ArrayList<String>();
			
			for (GerantDeClient gdc : this.ts.getClientList())
				pseudoList.add(gdc.getPseudo());
			
			for (GerantDeClient gdc : this.ts.getClientList())
				gdc.showMessage(pseudoList);
			
			

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}

	}
}
