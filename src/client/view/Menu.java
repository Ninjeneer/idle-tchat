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
	private JMenu mServeur;
	private JMenuItem miConnexion;

	public Menu(Window w) {
		this.w = w;
		this.mServeur = new JMenu("Serveur");
		this.miConnexion = new JMenuItem("Connexion");
		this.miConnexion.addActionListener(this);
		
			

		this.mServeur.add(this.miConnexion);

		this.add(this.mServeur);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.miConnexion) {
			String serverAddress = "";
			String pseudo = "";
			
			try {
				do {
					serverAddress = JOptionPane.showInputDialog(this.w, "Entrez l'adresse du serveur :\n(Ex : ci-di-715-23)", "Connexion au serveur", JOptionPane.PLAIN_MESSAGE);
				}while (serverAddress.equals(""));
				
				
				do {
					pseudo = JOptionPane.showInputDialog(this.w, "Choisissez un pseudo :", "Choix du pseudonyme", JOptionPane.PLAIN_MESSAGE);
				}while (pseudo.equals(""));
				
				
				try {
					this.w.getControler().getClientManager().setConnexionInformations(serverAddress, pseudo);
					this.w.getControler().getClientManager().startConnexion();
					
					System.out.println("Connecté !");
					this.w.getControler().getClientManager().setConnected(true);
					this.w.getControler().getClientManager().startGetMessage();
				} catch (Exception ex) {
					System.out.println("Impossible de se connecter");
				}
				
			} catch(Exception ex) {}
		}
	}
}
