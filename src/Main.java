import java.util.Scanner;

public class Main {

	public static void main(String[] args) {new Main();}

	Main() {
		System.out.println("Test Push");
		System.out.println("Test Push 2");
		System.out.println("Test Michael Push");
		System.out.println("Test Michael Pull");
		
		Calculator calc = new Calculator(10, 1000, 3, .40);
		System.out.println(calc.maxTurns());
		
	}
}
