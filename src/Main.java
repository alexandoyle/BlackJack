import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Main {

	public static void main(String[] args) throws IOException {new Main();}

	Main() throws IOException {
		//Calculate Run Time
		long start = System.currentTimeMillis();;
		
		
		ReaderWriter rw = new ReaderWriter("test.txt");
		rw.clearFile();
		
//*******************************************
//**    USER  INPUT  VARIABLES             **
//*******************************************
		double oddsOfWinning	= .4222;
		int startingBalance	 	= 100000;
		int minimumBet 			= 10;
		int numGames 			= 10000;


//*******************************************
//**    STARTING  VARIABLES                **
//*******************************************
		int numRounds 			= 1;
		int maxTurns 			= 1;
		int balance 			= 0;
		int numGamesSimulated 	= 0;
		int numRoundsSimulated	= 0;
		int numTurnsSimulated	= 0;
		
		double totalNet 		= 0;
		double multiplier 		= .9;
		
		boolean firstRound		= true;
		
		
		
//*********************************************************************************
//**    LOOP  THROUGH  DIFFERENT  NUMBER  OF  ROUNDS  BEFORE  LEAVING  TABLE     **
//*********************************************************************************
		for (;numRounds <= 25; numRounds++) {
			maxTurns = 1;
			
			
//*********************************************************************************
//**        LOOP  THROUGH  DIFFERENT  NUMBER  OF  MAX  TURNS  PER  ROUND         **
//*********************************************************************************
			for (;maxTurns <= 10; maxTurns++) {
				multiplier = .9;
				
				
//*********************************************************************************
//**    		LOOP  THROUGH  DIFFERENT  BET  MULTIPLIERS                       **
//*********************************************************************************
				for (;multiplier < 5; ) {
					multiplier = multiplier + .1;
					
					//Round Decimal to nearest .1
					DecimalFormat df = new DecimalFormat("#.#");      
					multiplier = Double.valueOf(df.format(multiplier));
					

//*********************************************************************************
//**    			LOOP  THROUGH  NEW  GAME SIMULATIONS                         **
//*********************************************************************************
					for (int a = 0; a < numGames; a++) {
						totalNet = 0;
						balance = 0;
						int nR = numRounds + 1;
						
						
//*********************************************************************************
//**    			    LOOP  THROUGH  NEW  ROUNDS  OF  BLACKJACK                **
//*********************************************************************************					
						for (int i = 1; i < nR; i++)
						{
							//First round of a new game
							if (i == 1) {
								
								//Create Calculator Object
								Calculator calc = new Calculator(minimumBet, startingBalance, multiplier, oddsOfWinning);
								
								//Update balance based on winnings/loss
								balance = calc.runRound(maxTurns);
								calc.setMoney(balance);
								//System.out.println("Balance after round " + i + ": " + calc.getMoney());

								//Display Starting Conditions
								if (firstRound) {

									firstRound = false;
									System.out.println("Starting Balance: " + startingBalance);
									System.out.println("Minimum Bet: " + minimumBet);
									System.out.println("Max Turns Per Round: " + maxTurns);

									//Calculate Odds of Losing Every Turn
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

							//All other rounds of a new game
							else {
								
								//Create Calculator Object
								Calculator calc = new Calculator(minimumBet, balance, multiplier, oddsOfWinning);
								
								//Update balance based on winnings/loss
								balance = calc.runRound(maxTurns);
								calc.setMoney(balance);
								numTurnsSimulated += calc.getNumTurns();
								
					
							}
							
							//Increase Number of Rounds simulated tracker
							numRoundsSimulated++;

						}

						//totalNet is used to find average net gain or loss after playing a bunch of time
						totalNet += (balance - startingBalance);

					}

					//Calculate Average Net Win or Loss after playing x number of Games
					double averageNet = totalNet / numGames;
					DecimalFormat an = new DecimalFormat("#.########");      
					averageNet = Double.valueOf(an.format(averageNet));

					//Print to txt file to be sorted
					
					rw.appendToFile(averageNet+" "+multiplier+" "+numRounds+" "+maxTurns);
					System.out.printf("%.8f", averageNet);
					System.out.print(" " + multiplier);
					System.out.print(" " + numRounds);
					System.out.print(" " + maxTurns);
					System.out.println();
					
					//Increase Number of Games simulated tracker
					numGamesSimulated += numGames;

				}
			}
		}
		
		//Print Number of Games and Rounds Simulated
		DecimalFormat commas = new DecimalFormat("#.#");   
		commas.setGroupingUsed(true); 
		commas.setGroupingSize(3);
		System.out.println();
		System.out.println("Full Games Simulated: " + commas.format(numGamesSimulated));
		System.out.println("Rounds simulated:     " + commas.format(numRoundsSimulated));
		System.out.println("Turns simulated:      " + commas.format(numTurnsSimulated));
		System.out.println();
		
		//Calculate & Print Program Execution Time
		long end = System.currentTimeMillis();;
		long milliseconds = end - start;
		long minutes = (milliseconds / 1000) / 60;
		long seconds = (milliseconds / 1000) % 60;
		milliseconds = milliseconds - (minutes * 60000) - (seconds * 1000);
		
		System.out.print("Program Execution Time: ");
		System.out.printf("%02d", minutes);
		System.out.println(":" + seconds + ":" + milliseconds);

	}
}
