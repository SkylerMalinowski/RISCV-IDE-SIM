package method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO
{	
	public static ArrayList<String> LoadFile(String path, String filename)
	{
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try
		{
			File file = new File(path+"\\"+filename);
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
	
	public static void main()
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler";
		String filename = "test.asm";
		ArrayList<String> programMemory = LoadFile(path,filename);
		for (String str : programMemory)
			System.out.println(str);
	}
}
