package client.controler;

import client.model.ClientManager;
import client.view.Window;

public class Controler {

	
	private Window w;
	private ClientManager cm;
	
	public Controler() {
		this.cm = new ClientManager(this);
		this.w = new Window(this);
		
	}
	
	public ClientManager getClientManager() {
		return this.cm;
	}
	
	public Window getWindow() {
		return this.w;
	}
	
	
	
	
	public static void main(String[] args) {
		new Controler();
	}
}
