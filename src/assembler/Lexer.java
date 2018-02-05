package assembler;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Take plain text file and create tokens
public class Lexer
{
	// Token IDs
	public static enum TokenType
	{
		// Token types
		REGISTER("x[0-9]+"),
		ALPHA("-?[A-z]+"),
		NUMERIC("-?[0-9]+"),
		SYMBOL("[`|~|!|@|$|%|^|&|*|(|)|-|_|=|+|[|]|{|}|\\|;|:|'|\"|.|/|<|>|?]"),
		COMMENT("#-?[ A-z0-9]+"),
		WHITESPACE("[' '|'\t'|'\f'|'\r'|'\n']+");

		public final String pattern;

		private TokenType(String pattern)
		{
			this.pattern = pattern;
		}
	}
	
	// Token definition
	public static class Token
	{
		public TokenType type;
		public String data;

		public Token(TokenType type, String data)
		{
			this.type = type;
			this.data = data;
		}

		@Override
		public String toString()
		{
			return String.format("(%s %s)", type.name(), data);
		}
	}

	public static ArrayList<Token> lexeme(String input)
	{
		// The tokens to return
		ArrayList<Token> tokens = new ArrayList<Token>();

		// Lexer logic begins here
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		// Token matching logic
		Matcher matcher = tokenPatterns.matcher(input);
		while (matcher.find())
		{
			if (matcher.group(TokenType.COMMENT.name()) != null)
			{
				tokens.add(new Token(TokenType.COMMENT, matcher.group(TokenType.COMMENT.name())));
				continue;
			}
			else if (matcher.group(TokenType.REGISTER.name()) != null)
			{
				tokens.add(new Token(TokenType.REGISTER, matcher.group(TokenType.REGISTER.name())));
				continue;
			}
			else if (matcher.group(TokenType.ALPHA.name()) != null)
			{
				tokens.add(new Token(TokenType.ALPHA, matcher.group(TokenType.ALPHA.name())));
				continue;
			}
			else if (matcher.group(TokenType.NUMERIC.name()) != null)
			{
				tokens.add(new Token(TokenType.NUMERIC, matcher.group(TokenType.NUMERIC.name())));
				continue;
			}
			else if (matcher.group(TokenType.SYMBOL.name()) != null)
			{
				tokens.add(new Token(TokenType.SYMBOL, matcher.group(TokenType.SYMBOL.name())));
				continue;
			}
			else if (matcher.group(TokenType.WHITESPACE.name()) != null)
				continue;
		}

		return tokens;
	}

	public static void main(String[] args)
	{
		String input = "lw x5, x0(x3)  # load word";

		// Create tokens and print them
		ArrayList<Token> tokens = lexeme(input);
		for (Token token : tokens)
			System.out.println(token);
	}
}
