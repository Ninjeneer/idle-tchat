package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import commandes.Commande;
import commandes.Coucou;
import commandes.MP;

public class TchatServer {

	private ServerSocket ss;
	private ArrayList<GerantDeClient> clientList;
	private HashMap<String, Commande> commandeListe;

	/**
	 * Crée un serveur de tchat
	 */
	public TchatServer() {

		try {
			// création du serveur
			System.out.println("Création du tchat..");
			this.ss = new ServerSocket(8003);
		} catch (Exception e) {
			System.out.println("Impossible de créer le serveur");
			e.printStackTrace();
		}

		this.clientList = new ArrayList<GerantDeClient>();
		this.commandeListe = new HashMap<String,Commande>();
		
		// ajout des commandes
		addCommande("coucou", new Coucou());
		addCommande("mp", new MP());

		while (true) {
			// attente du client
			Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(s);

			// création du client
			GerantDeClient gdc = new GerantDeClient(this, s);
			Thread tgdc = new Thread(gdc);
			tgdc.start();
		}
	}

	/**
	 * Envoie un message à tous les clients
	 * 
	 * @param sender client émetteur
	 * @param s      message envoyé
	 */
	public void sendMessage(GerantDeClient sender, String s) {
		// gestion des commandes
		if (s.startsWith("/")) {
			String trigger;
			
			if (s.contains(" "))
				 trigger = s.substring(1, s.indexOf(" "));
			else
				trigger = s.substring(1);
			
			this.commandeListe.get(trigger).onCommand(this, sender, s.split(" "));
		} else {
			for (GerantDeClient gdc : this.clientList) {
				if (gdc != sender)
					gdc.getPrintwriter().println(sender.getCouleur() + sender.getPseudo() + ": " + "\033[0m" + s);
			}
		}

		
		
	}

	/**
	 * Envoie une notification à tous les clients
	 * 
	 * @param sender
	 * @param s
	 */
	public void sendNotification(GerantDeClient sender, String s) {
		for (GerantDeClient gdc : this.clientList) {
			if (gdc != sender)
				gdc.getPrintwriter().println(s);
		}
	}

	/**
	 * Ajoute le client à la liste de clients
	 * 
	 * @param gdc Client
	 */
	public void addGerantDeClient(GerantDeClient gdc) {
		sendNotification(gdc, ">>> " + gdc.getPseudo() + " vient de rejoindre le serveur :D");
		this.clientList.add(gdc);
	}

	/**
	 * Supprime le client de la liste des clients
	 * 
	 * @param gdc
	 */
	public void delGerantDeClient(GerantDeClient gdc) {
		sendNotification(gdc, "<<< " + gdc.getPseudo() + " vient de quitter le serveur :(");
		this.clientList.remove(gdc);
	}
	
	private void addCommande(String trigger, Commande cmd) {
		this.commandeListe.put(trigger, cmd);
	}
	
	public ArrayList<GerantDeClient> getClientList() {
		return this.clientList;
	}

	
	
	
	public static void main(String[] args) {
		new TchatServer();
	}
}
