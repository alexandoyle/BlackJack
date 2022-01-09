import java.util.Scanner;

public class Main {

	public static void main(String[] args) {new Main();}

	Main() {


		int numGames = 100;
		double totalNet = 0;

		for (int a = 0; a < numGames; a++) {

			//Play 5 Rounds
			int balance = 10000;
			int minimumBet = 10;
			double multiplier = 3;
			double oddsOfWinning = .40;
			int numRounds = 10;
			int maxTurns = 5;



			double averageNet;

			int startingBalance = balance;
			int nR = numRounds + 1;
			for (int i = 1; i < nR; i++)
			{

				Calculator calc = new Calculator(minimumBet, balance, multiplier, oddsOfWinning);

				//int maxTurns = calc.maxTurns();


				//Display Starting info
				if (i == 1 && a == 0) {

					System.out.println("Starting Balance: " + startingBalance);
					System.out.println("Minimum Bet: " + minimumBet);
					System.out.println("Number of Games Simulated: " + numGames);
					//Calculate the Max Turns per Round
					System.out.println("Max Turns Per Round: " + maxTurns);

					//Calculate Odds of losing every turn
					double cumOdds = calc.cumOddsForTurn(maxTurns);
					double maxLossOdds = 1 - cumOdds;
					double maxLossOddsPercentage = maxLossOdds * 100;
					System.out.print("Odds of loosing every turn in a round: ");
					System.out.printf("%.3f%% %n", maxLossOddsPercentage);

					//Calculate odds of at least 1 max loss in after x Rounds
					double oddsOfWinningEveryRound = Math.pow(cumOdds, numRounds);
					double oddsOfAtLeastOneMaxLoss = 1 - oddsOfWinningEveryRound;
					double oddsOfAtLeastOneMaxLossPercentage = oddsOfAtLeastOneMaxLoss * 100;
					System.out.print("Odds of at least 1 max loss after " + numRounds + " total rounds: ");
					System.out.printf("%.3f%% %n ",oddsOfAtLeastOneMaxLossPercentage);
					System.out.println();
				}


				//Update balance based on winnings/loss
				balance = calc.runRound(maxTurns);
				calc.setMoney(balance);
				//System.out.println("Balance after round " + i + ": " + calc.getMoney());


			}

			System.out.println("Game: " + a + "\tNet:" + (balance - startingBalance));
			totalNet += (balance - startingBalance);

		}

		double averageNet = totalNet / numGames;
		System.out.println("Average Net: " + averageNet);



	}
}
