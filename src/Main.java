import java.text.DecimalFormat;
import java.util.Scanner;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Main {

	public static void main(String[] args) {new Main();}

	Main() {

		//Variables we want user to enter
		double oddsOfWinning = .4222;
		int startingBalance = 10000000;
		int minimumBet = 10;


		//These will not be changed
		int numGames = 10000;
		double totalNet = 0;
		double multiplier = .9;
		int numRounds = 1;
		int maxTurns = 1;
		int balance = 0;
		boolean firstRound = true;
		int numGamesSimulated = 0;
		int numRoundsSimulated = 0;
		


		//Loop through different number of Rounds played before leaving the table
		for (;numRounds <= 25; numRounds++) {

			maxTurns = 1;
			//Loop through different Max Turns per Round
			for (;maxTurns <= 10; maxTurns++) {

				multiplier = .9;
				//Loop Through Each Different Multiplier
				for (;multiplier < 5; ) {


					multiplier = multiplier + .1;
					//Round Decimal to nearest .1
					DecimalFormat df = new DecimalFormat("#.#");      
					multiplier = Double.valueOf(df.format(multiplier));

					//Beginning of Each Game (Walking up to a new BlackJack Table)
					for (int a = 0; a < numGames; a++) {
						totalNet = 0;
						balance = 0;


						int nR = numRounds + 1;
						for (int i = 1; i < nR; i++)
						{


							if (i == 1) {
								Calculator calc = new Calculator(minimumBet, startingBalance, multiplier, oddsOfWinning);
								//Update balance based on winnings/loss
								balance = calc.runRound(maxTurns);
								calc.setMoney(balance);
								//System.out.println("Balance after round " + i + ": " + calc.getMoney());

								//Display Starting info
								if (firstRound) {

									firstRound = false;
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
							}

							else {
								Calculator calc = new Calculator(minimumBet, balance, multiplier, oddsOfWinning);
								//Update balance based on winnings/loss
								balance = calc.runRound(maxTurns);
								calc.setMoney(balance);
								//System.out.println("Balance after round " + i + ": " + calc.getMoney());
							}



							//int maxTurns = calc.maxTurns();
							numRoundsSimulated++;

						}

						//System.out.println("Game: " + a + "\tNet:" + (balance - startingBalance));
						totalNet += (balance - startingBalance);

					}

					double averageNet = totalNet / numGames;
					DecimalFormat an = new DecimalFormat("#.########");      
					averageNet = Double.valueOf(an.format(averageNet));

					System.out.printf("%.8f", averageNet);
					System.out.print(" " + multiplier);
					System.out.print(" " + numRounds);
					System.out.print(" " + maxTurns);
					System.out.println();
					
					numGamesSimulated += numGames;

				}

			}
		}
		
		
		DecimalFormat commas = new DecimalFormat("#.#");   
		commas.setGroupingUsed(true); 
		commas.setGroupingSize(3);
		System.out.println();
		System.out.println("Number of full games simulated: " + commas.format(numGamesSimulated));
		System.out.println("Number of Rounds simulated: " + commas.format(numRoundsSimulated));
		

		
	
	}
}
