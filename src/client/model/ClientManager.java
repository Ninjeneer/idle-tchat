package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;

import client.controler.Controler;

public class ClientManager {
	
	private String serverAddress;
	private String pseudo;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private boolean connected;
	
	private Controler ctrl;

	public ClientManager(Controler ctrl) {
		this.ctrl = ctrl;
		this.connected = false;
	}
	
	public void startConnexion() throws NumberFormatException, UnknownHostException, IOException{
			this.s = new Socket(this.serverAddress.split(":")[0], Integer.parseInt(this.serverAddress.split(":")[1]));
			this.out = new PrintWriter(s.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// envoi des données de connexion
			this.out.println(this.pseudo);
	}
	
	public void sendMessage(String s) {
		try {
			this.out.println(s);
		} catch(Exception e) {}
	}
	
	public void startGetMessage() {
		CompletableFuture.runAsync(() -> 
			{
				try {
					String message;
					while(true) {
						message = in.readLine();
						
						for (String line : message.split("\n"))
							this.ctrl.getWindow().newMessage(line);
					}
						
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		);
	}
	
	public BufferedReader getInputStream() {
		return this.in;
	}
	
	
	public void setConnexionInformations(String serverAddress, String pseudo) {
		this.serverAddress = serverAddress;
		this.pseudo = pseudo;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public String getPseudo() {
		return pseudo;
	}
	
	public void setConnected(boolean b) {
		this.connected = b;
	}
	
	public boolean isConnected() {
		return this.connected;
	}
	
	
	

}
