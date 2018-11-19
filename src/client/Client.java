package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public Client(){
		
		String serveur,pseudo,port,temp;
		Scanner sc;
		System.out.println("Bienvenue sur Idle Tchat ");
		sc = new Scanner(System.in);
		
		do{
			System.out.print("A quel pseudo voulez-vous avoir : ");
			pseudo = sc.nextLine();
		}while(pseudo.equals(""));
		
		do{
			System.out.print("A quel serveur et port voulez-vous vous connecter : ");
			temp = sc.nextLine();
			serveur = temp.split(":")[0];
			port = temp.split(":")[1];
		}while(temp.equals(""));
		
		sc.close();
		try
		{
			System.out.println("Connexion au serveur " + serveur);
			Socket toServer = new Socket(serveur, Integer.parseInt(port)) ;
			System.out.println("Connecte");

			PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));

			out.println(pseudo);
			while(true){
				System.out.println(in.readLine());
				out.println(sc.nextLine());
			}
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
