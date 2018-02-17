package riscv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to represent a file and its associations for processing
 * 
 * @author Skyler Malinowski
 * @version February 2018
 */

public class Program
{
	private File file;
	private ArrayList<String> source;
	private ArrayList tokenList;
	private ArrayList<ErrorMessage> errorList;

	/**
	 * Constructor initializes all 'Program.class' variables, then sets file and source
	 * @param 'this.file'
	 */
	public Program(String filePath)
	{
		source = new ArrayList<String>();
		tokenList = new ArrayList();
		errorList = new ArrayList<ErrorMessage>();
		
		setFile(filePath);
		setSource(getFile().getAbsolutePath());
	}
	
	/**
	 * Method gets 'this.filename'
	 * @return 'this.file'
	 */
	public File getFile()
	{
		return this.file;
	}
	
	/**
	 * Method sets 'this.file'
	 * @param filename
	 */
	public void setFile(String filePath)
	{
		this.file = new File(filePath);
	}
	
	/**
	 * Method gets 'this.source'
	 * @return 'this.source'
	 */
	public ArrayList<String> getSource()
	{
		return this.source;
	}
	
	/**
	 * Method sets 'this.source' with raw text from file
	 * @param filename
	 */
	public void setSource(String filePath)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String currentLine = null;

			while ((currentLine = br.readLine()) != null)
			{
				//System.out.println(currentLine);
				this.source.add(currentLine);
			}
		}
		catch(FileNotFoundException ex)
		{
			ErrorMessage message = new ErrorMessage();
			message.Message("Unable to open file '" + filePath + "'");
			//System.out.println(message);
			this.errorList.add(message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method gets 'this.tokenList'
	 * @return this.tokenList
	 */
	public ArrayList getTokenList()
	{
		return this.tokenList;
	}
	
	/**
	 * Method sets 'this.tokenList'
	 * @param tokenList
	 */
	public void setTokenList(ArrayList tokenList)
	{
		this.tokenList = tokenList;
	}
	
	/**
	 * Method prints 'this.errorList'
	 */
	public void printErrorList()
	{
		for (ErrorMessage error : this.errorList)
		{
			System.out.println(error.toString() + "\n");
		}
	}
	
	/**
	 * Method appends 'this.errorList'
	 * @param errorList
	 */
	public void appendErrorList(ErrorMessage error)
	{
		this.errorList.add(error);
	}
	
	// Debugging
	public static void main(String[] args)
	{
		String filePath = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\test.asm";
		Program program = new Program(filePath);
	}

}
