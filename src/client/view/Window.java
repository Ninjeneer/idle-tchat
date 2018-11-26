package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.controler.Controler;
import serveur.GerantDeClient;

public class Window extends JFrame implements ActionListener {

	private JTextArea tpTchat;
	private JTextField tfInput;
	private JList<String> listClient;
	private JButton btSend;
	private Menu menu;

	private Controler ctrl;

	public Window(Controler ctrl) {
		this.ctrl = ctrl;
		
		

		// propriétés de la fenêtre
		this.setLayout(new BorderLayout(15, 5));
		this.setSize(800, 600);
		this.setTitle("Idle Tchat");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// création des widgets
		this.menu = new Menu(this);
		this.tpTchat = new JTextArea();
		this.tfInput = new JTextField();
		this.listClient = new JList<String>();
		this.listClient.setPreferredSize(new Dimension(150, 600));
		this.btSend = new JButton("Envoyer");
		this.btSend.addActionListener(this);
		

		JPanel inputContainer = new JPanel(new BorderLayout());
		inputContainer.add(this.tfInput);
		inputContainer.add(this.btSend, BorderLayout.EAST);

		// ajout des widgets
		this.add(this.menu, BorderLayout.NORTH);
		this.add(this.tpTchat);
		this.add(inputContainer, BorderLayout.SOUTH);
		this.add(this.listClient, BorderLayout.EAST);
		

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btSend) {
			if (!this.tfInput.getText().equals("")) {
				this.ctrl.getClientManager().sendMessage(this.tfInput.getText());
				this.tpTchat.append(this.ctrl.getClientManager().getPseudo() + " : " + this.tfInput.getText() + "\n");
				this.tfInput.setText("");
				
			}

		}
	}
	
	public void updateConnected(ArrayList<GerantDeClient> clientList) {
		String[] connectedList = new String[clientList.size()];
		for (int i = 0; i < connectedList.length; i++)
			connectedList[i] = clientList.get(i).getPseudo();
		
		this.listClient.setListData(connectedList);
	}
	
	public void newMessage(String s) {
		this.tpTchat.append(s + "\n");
	}

	public Controler getControler() {
		return this.ctrl;
	}
}
