package method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO
{	
	public static ArrayList<String> LoadFile(String path)
	{
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try
		{
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				//stringBuffer.append(line);
				//stringBuffer.append("\n");
				fileContents.add(line);
			}
			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return fileContents;
	}
	
	// main for debugging
	public static void main(String[] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler\\test.asm";
		ArrayList<String> program = LoadFile(path);
		for (String line : program)
			System.out.println(line);
	}
}
