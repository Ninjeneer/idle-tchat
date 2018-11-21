package serveur;

public class Affichage {
	
	public static final String bold = "\033[1m";
	public static final String italic = "\033[3m";
	public static final String reset = "\033[0m";
	
	//couleurs
	public static final String red = "\033[0;31m";
	public static final String green = "\033[0;32m";
	public static final String yellow = "\033[0;33m";
	public static final String blue = "\033[0;34m";
	public static final String pink = "\033[0;35m";
	public static final String cyan = "\033[0;36m";
	public static final String grey = "\033[0;37m";
	
	public static final String[] colors = new String[] { red, green, yellow, blue, pink, cyan, grey };
	
	
	
	public static String randomColor() {
		return Affichage.colors[(int)(Math.random() * Affichage.colors.length)];
	}

}
