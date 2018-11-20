package serveur;

public class Affichage {
	
	public static final String gras = "\033[1m";
	public static final String italic = "\033[3m";
	public static final String reset = "\033[0m";
	
	//couleurs
	public static final String rouge = "\033[0;31m";
	public static final String vert = "\033[0;32m";
	public static final String jaune = "\033[0;33m";
	public static final String bleu = "\033[0;34m";
	public static final String rose = "\033[0;35m";
	public static final String cyan = "\033[0;36m";
	public static final String gris = "\033[0;37m";
	
	public static final String[] couleurs = new String[] { rouge, vert, jaune, bleu, rose, cyan, gris };
	
	
	
	public static String randomColor() {
		return Affichage.couleurs[(int)(Math.random() * Affichage.couleurs.length)];
	}

}
