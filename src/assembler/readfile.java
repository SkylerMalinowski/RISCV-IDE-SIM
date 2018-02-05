package assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readfile
{
	public static void main(String[] args)
	{
		try
		{
			File file = new File("C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler\\test.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
