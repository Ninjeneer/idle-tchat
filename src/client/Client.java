package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import serveur.Serializer;
import tests.Lol;


public class Client {
	public Client(){
		
		String serveur,pseudo,port,temp;
		Scanner sc;
		System.out.println("Bienvenue sur Idle Tchat ");
		sc = new Scanner(System.in);
		
		// définition du pseudo
		do{
			System.out.print("A quel pseudo voulez-vous avoir : ");
			pseudo = sc.nextLine();
		}while(pseudo.equals(""));
		
		
		// définition du serveur
		do{
			System.out.print("A quel serveur et port voulez-vous vous connecter : ");
			temp = sc.nextLine();
			serveur = temp.split(":")[0];
			port = temp.split(":")[1];
		}while(temp.equals(""));
		

		// connexion au serveur
		try
		{
			System.out.println("Connexion au serveur " + serveur + "...");
			Socket toServer = new Socket(serveur, Integer.parseInt(port)) ;

			PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));

			// envoi du pseudo au serveur
			out.println(pseudo);
			
			// boucle principale
			String message = "";
			
			CompletableFuture.runAsync(() -> 
				{
					Object reponse = ""; 
					try {
						while(true) {
							reponse = Serializer.deserialize(in.readLine());
							
							if (reponse instanceof String)
								System.out.println(reponse);
						}
							
							
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			);
			
			do {
				message = sc.nextLine();
				out.println(message);
			}while(!message.equals("/quit"));
			
			// deconnexion
			System.out.println("Vous avez été déconnecté avec succès !");
		}
		catch(Exception e) 
		{
			System.out.println("ERREUR DETECTEE");
			e.printStackTrace();
		}
		
		sc.close();
	}
	
	public static void main(String[] args) {
		new Client();
	}

}
