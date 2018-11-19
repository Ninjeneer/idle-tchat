package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public Client(){
		
		String serveur;
		Scanner sc;
		System.out.println("Bienvenue sur Idle Tchat ");
		do{
			System.out.print("A quel serveur voulez-vous vous connecter : ");
			sc = new Scanner(System.in);
			serveur = sc.nextLine();
		}while(serveur.equals(""));
		
		sc.close();
		try
		{
			System.out.println("Connexion au serveur " + serveur);
			Socket toServer = new Socket(serveur, 9000) ;
			System.out.println("Connecte");

			PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));

			String message = in.readLine();
			System.out.println(message);
		}
		catch(IOException ioe) 
		{
			System.out.println("ERREUR DETECTEE");
			ioe.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}

}
