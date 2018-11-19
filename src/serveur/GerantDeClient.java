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
	private boolean tAlive;

	// informations relatives au client
	private String couleur;
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
		this.tAlive = true;
		this.couleur = Affichage.randomColor();

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

		// lecture du pseudo
		try {
			this.pseudo = this.in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// message de bienvenue
		try {
			this.out.println("   ___       _      _                      _____           _                _     \n" + 
					        "  |_ _|   __| |    | |     ___      o O O |_   _|   __    | |_     __ _    | |_   \n" + 
					        "   | |   / _` |    | |    / -_)    o        | |    / _|   | ' \\   / _` |   |  _|  \n" + 
					        "  |___|  \\__,_|   _|_|_   \\___|   TS__[O]  _|_|_   \\__|_  |_||_|  \\__,_|   _\\__|  \n" + 
					        "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" + 
					        "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");
			
			this.out.println("Bienvenue sur le serveur " + pseudo + " !");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ajoute le client à la liste des clients
		this.ts.addGerantDeClient(this);

		// boucle principale
		while (this.tAlive) {
			try {
				String message = this.in.readLine();

				if (message.equals("quit")) {
					this.ts.delGerantDeClient(this);
					this.tAlive = false;
				} else {
					this.ts.sendMessage(this, message);
				}

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

	/**
	 * Retourne le pseudo du client
	 * 
	 * @return Pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	/**
	 * Retourne la couleur du pseudo du client
	 * @return couleur
	 */
	public String getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Retourne le socket du client
	 * @return socket
	 */
	public Socket getSocket() {
		return this.s;
	}

	/**
	 * Affiche un message à l'utilisateur
	 * 
	 * @param s
	 */
	public void showMessage(String s) {
		this.out.println(s);
	}

}