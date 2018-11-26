package serveur;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tests.Lol;

public class Serializer {

	/**
	 * Serialize un objet sans passer par un fichier
	 * @param o objet à serializer
	 * @return string
	 */
	public static String serialize(Object o) {
		
		try {
			//Serialization
			byte[] serialized;
			String output = "";
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			oos.writeObject(o);
			serialized = baos.toByteArray();
			
			// transformation des bytes en string
			for (byte b : serialized)
				output += b + " ";
			
			oos.close();
			
			return output;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Déserialize un objet sans passer par un fichier
	 * @param serializedInput objet serialisé
	 * @return Object
	 */
	public static Object deserialize(String serializedInput) {
		
		if (serializedInput == null || serializedInput.equals(""))
			return null;
		
		try {
			//Deserialization		
			byte[] input = new byte[serializedInput.split(" ").length];
			// transformation de la serialization en bytes
			for (int i = 0; i < input.length; i++)
				input[i] = Byte.parseByte(serializedInput.split(" ")[i]);
			
		    ByteArrayInputStream bi = new ByteArrayInputStream(input);
		    ObjectInputStream si = new ObjectInputStream(bi);
		     
		   return si.readObject();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
