package tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		
		
		try {
			
			//Serialization
			byte[] serialized;
			String output = "";
			Lol test = new Lol();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			
			oos.writeObject(test);
			serialized = baos.toByteArray();
			
			for (byte b : serialized)
				output += b + " ";
			
			System.out.println(output);
			
			oos.close();
			
			
//			//Deserialization		
			byte[] input = new byte[output.split(" ").length];
			for (int i = 0; i < input.length; i++)
				input[i] = Byte.parseByte(output.split(" ")[i]);
			
		    ByteArrayInputStream bi = new ByteArrayInputStream(input);
		    ObjectInputStream si = new ObjectInputStream(bi);
		     
		    System.out.println(si.readObject() instanceof Lol);
		     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}