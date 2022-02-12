import java.io.*;
import java.util.Scanner;

public class ReaderWriter 
{
	String fileName;
	File myFile;
	
	String[] top3;
	
	public ReaderWriter(String name) throws IOException 
	{
		fileName = name;		
		myFile = new File(fileName);
		top3 = new String[3];
	}
	
	public void appendToFile(String values) throws IOException 
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		writer.newLine();
		writer.write(values);
		writer.close();
	}
	
	public void clearFile() throws IOException
	{
		FileWriter fw = new FileWriter(fileName, false); 
		BufferedWriter writer = new BufferedWriter(fw);
		writer.flush();
		writer.close();
		fw.close();
	}
	
	public void topThree()
	{
		Scanner myScanner;
		try 
		{
			File file = new File("C:\\Users\\alexa\\git\\BlackJack\\test.txt");
			myScanner = new Scanner(file);
			double firstValue = 0, secondValue = 0, thirdValue = 0;
			
			// myScanner.nextLine();
			
			while(myScanner.hasNextLine())
			{
				String line = myScanner.nextLine();
				//System.out.println(line);
				
				if(!line.equals(""))
				{
					String[] splitLine = line.split(" ");
					
					//System.out.println(splitLine[0]);
					
					double net = Double.parseDouble(splitLine[0]);
					
					if(net > firstValue)
					{
						thirdValue = secondValue;
						secondValue = firstValue;
						firstValue = net;
						
						top3[2] = top3[1];
						top3[1] = top3[0];
						top3[0] = line;
					}
					else if (net > secondValue)
					{
						thirdValue = secondValue;
						secondValue = net;
						
						top3[2] = top3[1];
						top3[1] = line;
					}
					else if (net > thirdValue)
					{
						thirdValue = net;
						
						top3[2] = line;
					}
				}
				
			}
			
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printTopThree()
	{
		System.out.println("Top 3 lines:");
		System.out.println(top3[0]);
		System.out.println(top3[1]);
		System.out.println(top3[2]);
	}
}
