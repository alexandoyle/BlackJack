import java.io.*;
import java.util.Scanner;

public class ReaderWriter 
{
	Scanner myScanner;
	String fileName;
	File myFile;
	
	public ReaderWriter(String name) throws IOException 
	{
		fileName = name;		
		myFile = new File(fileName);
		myScanner = new Scanner(myFile);
	}
	
	public String getLine()
	{
		return myScanner.nextLine();
	}
	public String [] getValues()
	{
		return myScanner.nextLine().split(" ");
	}
	
	public void appendToFile(String values) throws IOException 
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		writer.newLine();
		writer.write(values);
		writer.close();
	}
}
