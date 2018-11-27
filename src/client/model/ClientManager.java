package client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import client.controler.Controler;
import serveur.Affichage;
import serveur.GerantDeClient;
import serveur.Serializer;

public class ClientManager {

	private String serverAddress;
	private String pseudo;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private boolean connected;

	private Controler ctrl;

	/**
	 * Crée un client manager
	 * 
	 * @param ctrl controleur
	 */
	public ClientManager(Controler ctrl) {
		this.ctrl = ctrl;
		this.connected = false;
	}

	/**
	 * Établi la connexion avec le serveur cible
	 * 
	 * @throws NumberFormatException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void startConnexion() throws NumberFormatException, UnknownHostException, IOException {
		this.s = new Socket(this.serverAddress.split(":")[0], Integer.parseInt(this.serverAddress.split(":")[1]));
		this.out = new PrintWriter(s.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		// envoi des données de connexion
		this.out.println(this.pseudo);
	}

	/**
	 * Envoie un message au serveur
	 * 
	 * @param s message
	 */
	public void sendMessage(String s) {
		try {
			this.out.println(s);
		} catch (Exception e) {
		}
	}

	/**
	 * Commence la récupération asynchrone des messages
	 */
	public void startGetMessage() {
		CompletableFuture.runAsync(() -> {
			try {
				Object reponse;
				while (true) {
					reponse = Serializer.deserialize(in.readLine());

					// récéption d'un message simple
					if (reponse instanceof String) {
						String message = (String) reponse;

						for (String line : message.split("\n"))
							this.ctrl.getWindow().newMessage(line);
					} else if (reponse instanceof ArrayList<?>) {
						// récéption de la liste des utilisateurs connectés
						this.ctrl.getWindow().updateConnected((ArrayList<GerantDeClient>) reponse);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public BufferedReader getInputStream() {
		return this.in;
	}

	/**
	 * Défini les informations relatives à la connexion
	 * 
	 * @param serverAddress adresse IP du serveur
	 * @param pseudo        pseudo du client
	 */
	public void setConnexionInformations(String serverAddress, String pseudo) {
		this.serverAddress = serverAddress;
		this.pseudo = pseudo;
	}

	/**
	 * Retourne l'adresse du serveur cible
	 * 
	 * @return adresse IP
	 */
	public String getServerAddress() {
		return serverAddress;
	}

	/**
	 * Retourne le pseudo du client
	 * 
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Défini si le client est connecté
	 * 
	 * @param b vrai si connecté
	 */
	public void setConnected(boolean b) {
		this.connected = b;
	}

	/**
	 * Retourne si le client est connecté
	 * 
	 * @return vrai si connecté
	 */
	public boolean isConnected() {
		return this.connected;
	}

}
