package assembler;

import java.util.ArrayList;

import assembler.Lexer.Token;
import method.FileIO;

// Take lexeme tokens and filter through AST via DOM
public class Parser
{
	public ArrayList<String> StatusLog = new ArrayList<String>();
	
	// Evaluate DOM based AST
	public void Evaluate(ArrayList<Token> tokenStream)
	{
		for (Token token : tokenStream)
		{
			if (token.type == Lexer.TokenType.ALPHA)
			{
				
			}
			else if (token.type == Lexer.TokenType.DIRECTIVE)
			{
				
			}
			else
			{
				
			}
		}
	}
	
	// Debugging: main()
	public static void main(String[] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler\\test.asm";
		
		ArrayList<String> program = FileIO.LoadFile(path);
		Parser parser = new Parser();
		
		for (String line : program)
		{
			// Generate tokens array
			ArrayList<Token> tokenStream = Lexer.lex(line);
			
			// Debugging: Print token array
			//System.out.println(tokenStream);
			
			parser.Evaluate(tokenStream);
		}
		System.out.println(parser.StatusLog);
	}
}
