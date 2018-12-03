package commandes;

import serveur.GerantDeClient;
import serveur.TchatServer;

public interface Commande {
	
	/**
	 * Méthode exécutée à chaque appel d'une commande
	 * @param ts Serveur
	 * @param sender client exécutant la commande
	 * @param args paramètres de la commande
	 * @return true si la commande est bien exécutée
	 */
	boolean onCommand(TchatServer ts, GerantDeClient sender, String[] args);
	
	/**
	 * Affiche ou non la commande dans la liste /help
	 * @return true si elle est affichable
	 */
	boolean isDisplayable();
	
	/**
	 * Renvoi à l'utilisateur de le message d'erreur de la commande
	 * @return message d'erreur
	 */
	String getError();
	
	/**
	 * Renvoi à l'utilisateur la description de la commande
	 * @return description
	 */
	String getDescription();
}
