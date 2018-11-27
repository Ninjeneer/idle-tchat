package serveur;

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
			for (GerantDeClient gdc : this.ts.getClientList())
				gdc.showMessage(ts.getClientList());

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}

	}
}
