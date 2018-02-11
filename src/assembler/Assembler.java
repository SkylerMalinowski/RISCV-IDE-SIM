package assembler;

import method.FileIO;
import assembler.Lexer.Token;

import java.util.ArrayList;

public class Assembler
{
	// File raw text to be assembled
	public static ArrayList<String> program;
	// Base and extensions in use
	public static ArrayList<String> Resources;
	
	public void SetProgram(String path)
	{
		this.program = FileIO.LoadFile(path);
	}
	
	public void Assemble(String path)
	{
		SetProgram(path);
		Parser parser = new Parser();
		
		for (String line : program)
		{
			// Generate tokens array
			ArrayList<Token> lexemes = Lexer.lex(line);
			
			// Debugging: Print token array
			//System.out.println(lexemes);
			
			parser.Evaluate(lexemes);
		}
	}
	
	// Debugging: main()
	public static void main(String[] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler\\test.asm";
		
		program = FileIO.LoadFile(path);
		Parser parser = new Parser();
		/*
		for (String line : programMemory)
			System.out.println(line);
		*/
		
		//ArrayList<String> errorLog;
		
		for (String line : program)
		{
			// Generate tokens array
			ArrayList<Token> lexemes = Lexer.lex(line);
			
			// Debugging: Print token array
			//System.out.println(lexemes);
			
			parser.Evaluate(lexemes);
		}
	}
}
