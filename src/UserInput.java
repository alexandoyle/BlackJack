import java.util.Scanner;

public class UserInput
{

	double oddsOfWinning;
	int startingBalance;
	int minimumBet;
	int numGames;

	public UserInput(Double odds, int sb, int mb, int ng)
	{
		oddsOfWinning = odds;
		startingBalance = sb;
		minimumBet = mb;
		numGames = ng;
	}

	public void newInput()
	{
		Scanner in = new Scanner(System.in);

		boolean ValidInput = false;

		//Loop Until Valid Input is Given
		for (int i = 0; !ValidInput; i++)
		{
			if (i == 0) 
			{
				System.out.println("BlackJack Table Starting Conditions");
				System.out.println("-----------------------------------");
				System.out.println("Odds of Winning: " + oddsOfWinning * 100 + "%");
				System.out.println("Starting balance: $" + startingBalance);
				System.out.println("Table's minimum bet: $" + minimumBet);
				System.out.println("Num Games Simulated: " + numGames);
				System.out.println("-----------------------------------");
				System.out.println("Would you like to enter new starting info? (Y/N):");
			}
			else
			{
				System.out.println("Please just enter Y or N:");
			}
			String s = in.nextLine();
			System.out.println("You entered string " + s);

			String NoUpperCase = "N";
			String NoLowerCase = "n";
			int compare1 = stringCompare(s, NoUpperCase);
			int compare2 = stringCompare(s, NoLowerCase);

			if (compare1 == 0 || compare2 == 0)
			{
				ValidInput = true;
				// closing scanner
				in.close();
				return;
			}

			String YesUpperCase = "Y";
			String YesLowerCase = "y";
			compare1 = stringCompare(s, YesUpperCase); 
			compare1 = stringCompare(s, YesLowerCase); 
			if (compare1 == 0 || compare2 == 0)
			{
				ValidInput = true;

			}



		}
		// closing scanner
		in.close();
		return;
	}

	public static int stringCompare(String str1, String str2)
	{

		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int)str1.charAt(i);
			int str2_ch = (int)str2.charAt(i);

			if (str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}

		// Edge case for strings like
		if (l1 != l2) {
			return l1 - l2;
		}

		// If none of the above conditions is true,
		// it implies both the strings are equal
		else {
			return 0;
		}
	}

}