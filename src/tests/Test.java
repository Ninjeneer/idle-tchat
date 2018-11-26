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
			Lol test = new Lol();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			
			oos.writeObject(test);
			serialized = baos.toByteArray();
			oos.close();
			
			//Deserialization			
		    ByteArrayInputStream bi = new ByteArrayInputStream(serialized);
		    ObjectInputStream si = new ObjectInputStream(bi);
		     
		    System.out.println(si.readObject() instanceof Lol);
		     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
