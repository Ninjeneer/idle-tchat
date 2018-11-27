package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar implements ActionListener {

	private Window w;
	private JMenu mClient;
	private JMenuItem miConnect;
	private JMenuItem miDisconnect;

	private JMenu mServer;
	private JMenuItem miCreate;
	private JMenuItem miStop;

	/**
	 * Crée une barre de menu
	 * 
	 * @param w frame mère
	 */
	public Menu(Window w) {
		this.w = w;
		this.mClient = new JMenu("Client");
		this.miConnect = new JMenuItem("Connexion");
		this.miConnect.addActionListener(this);

		this.miDisconnect = new JMenuItem("Déconnexion");
		this.miDisconnect.setEnabled(false);
		this.miDisconnect.addActionListener(this);

		this.mServer = new JMenu("Serveur");
		this.miCreate = new JMenuItem("Créer un serveur");
		this.miStop = new JMenuItem("Arrêter le serveur");

		this.mClient.add(this.miConnect);
		this.mClient.add(this.miDisconnect);

		this.mServer.add(this.miCreate);
		this.mServer.add(this.miStop);

		this.add(this.mClient);
		this.add(this.mServer);
	}

	/**
	 * Traite les interactions avec le menu
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.miConnect) {
			String serverAddress = "";
			String pseudo = "";

			try {
				do {
					serverAddress = JOptionPane.showInputDialog(this.w,
							"Entrez l'adresse du serveur :\n(Ex : ci-di-715-23)", "Connexion au serveur",
							JOptionPane.PLAIN_MESSAGE);
				} while (serverAddress.equals(""));

				do {
					pseudo = JOptionPane.showInputDialog(this.w, "Choisissez un pseudo :", "Choix du pseudonyme",
							JOptionPane.PLAIN_MESSAGE);
				} while (pseudo.equals(""));

				try {
					this.w.getControler().getClientManager().setConnexionInformations(serverAddress, pseudo);
					this.w.getControler().getClientManager().startConnexion();

					this.w.getControler().getClientManager().setConnected(true);
					this.w.getControler().getClientManager().startGetMessage();
					this.miConnect.setEnabled(false);
					this.miDisconnect.setEnabled(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this.w, "Erreur : impossible de se connecter au serveur !", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception ex) {
			}
		} else if (e.getSource() == this.miDisconnect) {
			this.w.getControler().getClientManager().sendMessage("/quit");
			this.w.clearTchat();
			this.miConnect.setEnabled(true);
			this.miDisconnect.setEnabled(false);
		}
	}
}
