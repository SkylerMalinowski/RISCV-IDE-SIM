package assembler;

import method.FileIO;

import java.util.ArrayList;

public class Assembler
{
	// main for testing
	public static void main(String[] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler";
		String filename = "test.asm";
		
		ArrayList<String> programMemory = FileIO.LoadFile(path,filename);
		/*
		for (String line : programMemory)
			System.out.println(line);
		*/
		
		//ArrayList<String> errorLog;
		
		Lexer.main(programMemory);
	}
}
