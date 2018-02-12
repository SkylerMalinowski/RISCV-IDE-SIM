package assembler;

import assembler.Parser;

import java.util.ArrayList;

public class Assembler
{
	// File raw text to be assembled
	public static ArrayList<String> program;
	// Base and extensions in use
	public static ArrayList<String> Resources;
	/*
	public void Assemble(String path)
	{
		for (String line : program)
		{
			// Generate tokens array
			ArrayList<Token> lexemes = Lexer.lex(line);
			
			// Debugging: Print token array
			System.out.println(lexemes);
		}
	}
	*/
	// Debugging: main()
	public static void main(String[] args)
	{
		String filePath = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\test.asm";
		
		Parser.theThing(filePath);
	}
}
