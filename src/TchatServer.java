import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TchatServer {

	
	private ServerSocket ss;
	private ArrayList<PrintWriter> clientList;
	
	
	/**
	 * Crée un serveur de tchat
	 */
	public TchatServer() {
		
		try {
			//création du serveur
			System.out.println("Création du tchat..");
			this.ss = new ServerSocket(8003);
		} catch(Exception e) {
			System.out.println("Impossible de créer le serveur");
			e.printStackTrace();
		}
		
		
		this.clientList = new ArrayList<PrintWriter>();
		
		
		while (true) {
			//attente du client
			Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(s);
			
			
			//création du client
			GerantDeClient gdc = new GerantDeClient(this, s);
			Thread tgdc = new Thread(gdc);
			tgdc.start();
		}
	}
	

	/**
	 * Envoie un message à tous les clients
	 * @param sender client émetteur
	 * @param s      message envoyé
	 */
	public void sendMessage(PrintWriter sender, String s) {
		if (s.equals("quit")) {
			this.clientList.remove(sender);
			return;
		}
		
		for (PrintWriter pw : this.clientList) {
			if (pw != sender)
				pw.println(s);
		}	
	}
	
	
	/**
	 * Ajoute le printwriter du client à la liste de clients
	 * @param pw PrintWriter client
	 */
	public void addPrintWriter(PrintWriter pw) {
		this.clientList.add(pw);
	}
	
	
	
	
	public static void main(String[] args) {
		new TchatServer();
	}
}
