package client.view;

import java.io.IOException;

import javax.swing.JList;

import client.controler.Controler;

public class RefreshClientList implements Runnable {
	
	private Controler ctrl;
	private JList<String> list;
	
	public RefreshClientList(Controler ctrl, JList list) {
		this.ctrl = ctrl;
		this.list = list;
	}

	@Override
	public void run() {
		
		while (true) {
			System.out.println(this.ctrl.getClientManager().isConnected());
			if (this.ctrl.getClientManager().isConnected()) {
				System.out.println("lol");
				try {
					this.ctrl.getClientManager().sendMessage("/who");
					String rawConnected = this.ctrl.getClientManager().getInputStream().readLine();
					String[] connected = new String[rawConnected.split("->").length];
					
					for (int i = 1; i < connected.length; i++)
						connected[i-1] = rawConnected.split("->")[i];
					
					System.out.println(connected.length);
					this.list.setListData(connected);
						
						
					
				} catch (IOException e) {
					
				}
			}
			
			try { Thread.sleep(3000); } catch(Exception e) {}
		}
		
		
	}

}
