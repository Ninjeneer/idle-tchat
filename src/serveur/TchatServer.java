package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import commandes.ChangerCouleur;
import commandes.Commande;
import commandes.Coucou;
import commandes.Help;
import commandes.MP;
import commandes.Me;
import commandes.Nick;
import commandes.Quit;
import commandes.Who;
import commandes.Wizz;
import commandes.admin.AdminHelp;
import commandes.admin.AdminLogin;
import commandes.admin.AdminLogoff;
import commandes.admin.BlockCommand;
import commandes.admin.GetInfo;
import commandes.admin.Kick;
import commandes.admin.KickAll;
import commandes.admin.Mute;
import commandes.admin.MuteAll;
import commandes.admin.Say;
import commandes.admin.UnBlockCommand;
import commandes.admin.UnMute;
import commandes.admin.UnMuteAll;
import commandes.admin.WizzAll;

public class TchatServer {

	private ServerSocket ss;
	private ArrayList<GerantDeClient> clientList;
	private HashMap<String, Commande> commandListe;
	private long lastConnexion;

	/**
	 * Crée un serveur de tchat
	 */
	public TchatServer(int port) {

		try {
			// création du serveur
			System.out.println("Création du tchat..");
			this.ss = new ServerSocket(port);
			System.out.println("Tchat créé !");
		} catch (Exception e) {
			System.out.println("Impossible de créer le serveur");
			e.printStackTrace();
		}

		this.clientList = new ArrayList<GerantDeClient>();
		this.commandListe = new HashMap<String, Commande>();

		// ajout des commandes
		addCommand("coucou", new Coucou());
		addCommand("mp", new MP());
		addCommand("wizz", new Wizz());
		addCommand("help", new Help());
		addCommand("nick", new Nick());
		addCommand("quit", new Quit());
		addCommand("me", new Me());
		addCommand("who", new Who());
		addCommand("changercouleur", new ChangerCouleur());
		
		// commandes admin
		addCommand("kick", new Kick());
		addCommand("adminlogin", new AdminLogin());
		addCommand("adminlogoff", new AdminLogoff());
		addCommand("mute", new Mute());
		addCommand("unmute", new UnMute());
		addCommand("say", new Say());
		addCommand("adminhelp", new AdminHelp());
		addCommand("getinfo", new GetInfo());
		addCommand("wizzall", new WizzAll());
		addCommand("kickall", new KickAll());
		addCommand("muteall", new MuteAll());
		addCommand("unmuteall", new UnMuteAll());
		addCommand("blockcommand", new BlockCommand());
		addCommand("unblockcommand", new UnBlockCommand());

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

			// si la commande n'existe pas
			if (!this.commandListe.containsKey(trigger)) {
				sender.showMessage(Affichage.bold + "ERREUR : cette commande n'existe pas" + Affichage.reset);
				return;
			}
			
			if (!sender.isComandAllowed()) {
				sender.showMessage(Affichage.red + "ERREUR : vos commandes ont été bloquées par un administrateur !" + Affichage.reset);
				return;
			}

			// si la commande est mal utilisée
			if (!this.commandListe.get(trigger).onCommand(this, sender, s.split(" ")) && sender.isComandAllowed())
				sender.showMessage(Affichage.bold + this.commandListe.get(trigger).getError() + Affichage.reset);

		} else {
			// envoi du message
			for (GerantDeClient gdc : this.clientList) {
				if (gdc != sender && !sender.isMuted() && sender.isAlive())
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
		sendNotification(gdc, ">>> " + gdc.getPseudo() + "(" + gdc.getSocket().getInetAddress()
				+ ") vient de rejoindre le serveur :D");
		gdc.showMessage(Affichage.bold + "Tapez /help [commande] afin d'obtenir de l'aide" + Affichage.reset);
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

	/**
	 * Ajoute une commande au serveur
	 * 
	 * @param trigger commande utilisateur
	 * @param cmd     commande déclenchée
	 */
	private void addCommand(String trigger, Commande cmd) {
		this.commandListe.put(trigger, cmd);
	}

	/**
	 * Retourne la liste des clients
	 * 
	 * @return liste de clients
	 */
	public ArrayList<GerantDeClient> getClientList() {
		return this.clientList;
	}

	/**
	 * Retourne la liste des commandes
	 * 
	 * @return liste des commandes
	 */
	public HashMap<String, Commande> getCommandeList() {
		return this.commandListe;
	}

	public static void main(String[] args) {
		new TchatServer(8003);
	}
}
