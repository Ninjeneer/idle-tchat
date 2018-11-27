package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import utils.Affichage;

public class GerantDeClient implements Runnable, Serializable {

	private transient TchatServer ts;
	private transient Socket s;
	private transient PrintWriter out;
	private transient BufferedReader in;
	private boolean tAlive;

	// informations relatives au client
	private String color;
	private String pseudo;
	private boolean isAdmin;
	private boolean isMuted;
	private boolean isCommandAllowed;
	private long lastMessage; // timestamp

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
		this.isCommandAllowed = true;
		this.color = Affichage.randomColor();

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
			if (this.pseudo.equals(gdc.getPseudo()) || this.pseudo.contains(" ") || this.pseudo.length() < 3
					|| this.pseudo.length() > 20) {
				showMessage(Affichage.bold + "ERREUR : Ce pseudo est déjà utilisé ou invalide ! Quittez et recommencez."
						+ Affichage.reset);
				return;
			}

		// bloque les multiples connexions par IP
		if (!TchatServer.debug_mode)
			for (int i = 0; i < this.ts.getClientList().size(); i++)
				if (this.s.getInetAddress().getHostAddress()
						.equals(this.ts.getClientList().get(i).getSocket().getInetAddress().getHostAddress())) {
					showMessage(Affichage.red + "ERREUR : un compte utilisant cette adresse IP est déjà connecté !"
							+ Affichage.reset);
					return;
				}

		// message de bienvenue
		try {
			showMessage(Affichage.yellow
					+ "   ___       _      _                      _____           _                _     \n"
					+ "  |_ _|   __| |    | |     ___      o O O |_   _|   __    | |_     __ _    | |_   \n"
					+ "   | |   / _` |    | |    / -_)    o        | |    / _|   | ' \\   / _` |   |  _|  \n"
					+ "  |___|  \\__,_|   _|_|_   \\___|   TS__[O]  _|_|_   \\__|_  |_||_|  \\__,_|   _\\__|  \n"
					+ "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
					+ "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' "
					+ Affichage.reset);

			showMessage("Bienvenue sur le serveur " + pseudo + " !");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ajoute le client à la liste des clients
		this.ts.addGerantDeClient(this);

		// boucle principale
		while (this.tAlive) {
			try {
				String message = in.readLine();

				if (System.currentTimeMillis() - this.lastMessage >= 500) {
					this.ts.sendMessage(this, message);
				} else {
					showMessage(Affichage.red + "ATTENTION : évitez le spam !" + Affichage.reset);
				}

				this.lastMessage = System.currentTimeMillis();

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
	public void showMessage(Object s) {
		this.out.println(Serializer.serialize(s));
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
		return this.color;
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
	public void disconnect() {
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
	 * @param b muet
	 */
	public void setMuted(boolean b) {
		this.isMuted = b;
	}

	/**
	 * Change la couleur du pseudo
	 * 
	 * @param color caractère couleur
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Retourne vrai si le client est encore connecté
	 * 
	 * @return client encore connecté
	 */
	public boolean isAlive() {
		return this.tAlive;
	}

	public boolean isComandAllowed() {
		return this.isCommandAllowed;
	}

	public void setCommandAllowed(boolean b) {
		this.isCommandAllowed = b;
	}

}