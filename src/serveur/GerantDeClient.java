package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerantDeClient implements Runnable {

	private TchatServer ts;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;

	// informations relatives au client
	private String pseudo;
	private String ip;

	
	
	/**
	 * Crée un gérant de client
	 * 
	 * @param ts
	 * @param s
	 */
	public GerantDeClient(TchatServer ts, Socket s) {
		this.ts = ts;
		this.s = s;

		try {
			this.out = new PrintWriter(s.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Éxecute le Thread
	 */
	@Override
	public void run() {
		this.ts.addPrintWriter(this.out);

		// lecture du pseudo
		try {
			this.pseudo = this.in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		// message de bienvenue
		try {
			this.out.println("Bienvenue " + pseudo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// notification générale de connexion
		try {
			this.ts.sendMessage(this.out, pseudo + " vient de se connecter ! (" + this.s.getInetAddress().getHostAddress() + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		while (true) {
			try {
				String message = this.in.readLine();
				
				this.ts.sendMessage(this.out, pseudo + ": " + message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Retourne le printwriter
	 * 
	 * @return PrintWriter
	 */
	public PrintWriter getPrintwriter() {
		return this.out;
	}

}