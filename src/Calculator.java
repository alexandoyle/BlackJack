import java.lang.Math;

public class Calculator 
{
	int minimumBet;
	int money;
	double multiplier;
	double odds;
	
	public Calculator(int mb, int m, double mult, double o)
	{
		minimumBet = mb;
		money = m;
		multiplier = mult;
		odds = o;
	}
	
	public int getMinBet()
	{
		return minimumBet;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public double getMult()
	{
		return multiplier;
	}
	
	public double getOdds()
	{
		return odds;
	}
	
	public void setMinBet(int x)
	{
		minimumBet = x;
	}
	
	public void setMoney(int x)
	{
		money = x;
	}
	
	public void addMoney(int x)
	{
		money += x;
	}
	
	public void setMult(double x)
	{
		multiplier = x;
	}
	
	public void setOdds(double x)
	{
		odds = x;
	}
	
	public double oddsForTurn(int t)
	{
		if(t == 1)
		{
			return odds;
		}
		else
		{
			return Math.pow(1-odds, t-1)*odds;
		}
	}
	
	public double cumOddsForTurn(int t)
	{
		double sum = 0;
		for(int i = t; i >= 1; i--)
		{
			sum += oddsForTurn(i);
		}
		return sum;
	}
	
	public int netWinsForTurn(int t)
	{
		int sum = 0;
		for(int i = 1; i < t; i++)
		{
			sum += minimumBet*(Math.pow(multiplier, i-1));
		}
		return (int) (minimumBet*(Math.pow(multiplier, t-1)) - sum);
	}
	
	public int maxTurns()
	{
		int i = 0;
		int sum = 0;
		
		while(sum <= money)
		{
			++i;
			System.out.println(i);
			if(i == 1)
			{
				sum = minimumBet;
				System.out.println(sum);
			}
			else
			{
				sum += minimumBet * Math.pow(multiplier, i-1);
				System.out.println(sum);
			}
		}
		return i-1;
	}
	
}
