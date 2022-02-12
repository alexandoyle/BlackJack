import java.util.Scanner;

public class UserInput
{
	public UserInput()
	{
		
	}
	
	public void newInput()
	{
		Scanner in = new Scanner(System.in);
		 
		System.out.println("Would you like to enter new starting info? (Y/N):");
        String s = in.nextLine();
        System.out.println("You entered string " + s);
        
        
       
          // closing scanner
          in.close();
	}
	
}