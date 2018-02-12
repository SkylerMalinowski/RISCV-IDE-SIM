package method;

import java.io.*;
import java.util.ArrayList;

public class FileIO
{	
	public static ArrayList<String> LoadFile(String fileName)
	{
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			while((line = bufferedReader.readLine()) != null)
			{
				//System.out.println(line);
				fileContents.add(line);
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return fileContents;
	}
	
	// Debuggingv: main()
	public static void main(String[] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\method\\test.asm";
		ArrayList<String> program = LoadFile(path);
		
		for (String line : program)
			System.out.println(line);
	}
}
