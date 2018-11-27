package tests;

public class Test {

	public static void main(String[] args) {
		
		
		String input = "[format=red]bonjour[/format]";
		input.replace("[format=red]", "");
		
		System.out.println(input);
		
	}
}
