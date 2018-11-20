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
	private boolean isAdmin;
	private boolean isMuted;

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

		for (GerantDeClient gdc : this.ts.getClientList())
			if (this.pseudo.equals(gdc.getPseudo())) {
				this.out.println(Affichage.gras + "ERREUR : Ce pseudo est déjà utilisé ! Quittez et recommencez."
						+ Affichage.reset);
				return;
			}

		// message de bienvenue
		try {
			this.out.println("   ___       _      _                      _____           _                _     \n"
					+ "  |_ _|   __| |    | |     ___      o O O |_   _|   __    | |_     __ _    | |_   \n"
					+ "   | |   / _` |    | |    / -_)    o        | |    / _|   | ' \\   / _` |   |  _|  \n"
					+ "  |___|  \\__,_|   _|_|_   \\___|   TS__[O]  _|_|_   \\__|_  |_||_|  \\__,_|   _\\__|  \n"
					+ "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
					+ "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");

			this.out.println("Bienvenue sur le serveur " + pseudo + " !");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ajoute le client à la liste des clients
		this.ts.addGerantDeClient(this);

		// boucle principale
		while (this.tAlive) {
			try {
				this.ts.sendMessage(this, this.in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Affiche un message à l'utilisateur
	 * 
	 * @param s
	 */
	public void showMessage(String s) {
		this.out.println(s);
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
	 * 
	 * @return couleur
	 */
	public String getCouleur() {
		return this.couleur;
	}

	/**
	 * Retourne le socket du client
	 * 
	 * @return socket
	 */
	public Socket getSocket() {
		return this.s;
	}

	/**
	 * Défini le pseudo du client
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Déconnecte le client
	 */
	public void deconnecter() {
		this.tAlive = false;
		this.ts.delGerantDeClient(this);
	}

	/**
	 * Retourne vrai si le client est administrateur
	 * 
	 * @return est admin
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}

	/**
	 * Défini si un client est administrateur
	 * 
	 * @param b
	 */
	public void setAdmin(boolean b) {
		this.isAdmin = b;
	}

	/**
	 * Retourne si un client est muet
	 * 
	 * @return
	 */
	public boolean isMuted() {
		return this.isMuted;
	}

	/**
	 * Défini si un client est muet
	 * 
	 * @param b
	 */
	public void setMuted(boolean b) {
		this.isMuted = b;
	}

}