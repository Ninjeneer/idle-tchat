package serveur;

public class RefreshClientList implements Runnable {

	private TchatServer ts;
	
	public RefreshClientList(TchatServer ts) {
		this.ts = ts;
	}

	@Override
	public void run() {
		
		while(true) {
			for (GerantDeClient gdc : this.ts.getClientList())
				gdc.showMessage(ts.getClientList());
			
			System.out.println(ts.getClientList().size());
			try { Thread.sleep(2000); }catch(Exception e) {}
		}
		
	}
}
