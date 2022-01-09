import java.util.Scanner;

public class Main {

	public static void main(String[] args) {new Main();}

	Main() {
		
		int balance = 1210;
		int minimumBet = 10;
		double multiplier = 3;
		double oddsOfWinning = .40;
		int numRounds = 5;
		
		int startingBalance = balance;
		int nR = numRounds + 1;
		//Play 5 Rounds
		for (int i = 1; i < nR; i++)
		{
			
				Calculator calc = new Calculator(minimumBet, balance, multiplier, oddsOfWinning);
				
				int maxTurns = calc.maxTurns();
				
				//Display Starting info
				if (i == 1) {
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
				System.out.println("Balance after round " + i + ": " + calc.getMoney());
				
			
		}
		
		System.out.println("Net:" + (balance - startingBalance));
	}
}
