import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GerantDeClient implements Runnable {
	
	private Thread t;
	private TchatServer ts;
	private PrintWriter out;
	private BufferedReader in;
	
	/**
	 * Crée un gérant de client
	 * @param ts
	 * @param ss
	 */
	public GerantDeClient(TchatServer ts, Socket ss) {
		this.ts = ts;
		
		try {
			this.out = new PrintWriter(ss.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Éxecute le Thread
	 */
	@Override
	public void run() {
		this.ts.addPrintWriter(this.out);
		
		while (true) {
			try {
				this.ts.sendMessage(this.out, this.in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Retourne le printwriter
	 * @return PrintWriter
	 */
	public PrintWriter getPrintwriter() {
		return this.out;
	}

}