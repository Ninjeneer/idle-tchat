package tests;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public ServerTest() {
		try {
			ServerSocket ss = new ServerSocket(9000);
			Socket s = ss.accept();
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			
			
			try {
				String message = new String("bonjour");
				ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream());
				oos.writeObject(message.getBytes());
				
				out.println(oos);
				System.out.println(oos);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ServerTest();
	}
}
