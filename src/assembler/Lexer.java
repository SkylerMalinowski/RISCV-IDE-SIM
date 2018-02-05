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
		// Token types cannot have underscores
		ALPHA("-?[A-Za-z]+"),
		NUMERIC("-?[0-9]+"),
		SYMBOL("[`|~|!|@|#|$|%|^|&|*|(|)|_|+]"),
		WHITESPACE("[ \t\f\r\n]+");

		public final String pattern;

		private TokenType(String pattern)
		{
			this.pattern = pattern;
		}
	}
	
	// What a token is
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

	public static ArrayList<Token> lex(String input)
	{
		// The tokens to return
		ArrayList<Token> tokens = new ArrayList<Token>();

		// Lexer logic begins here
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		// Begin matching tokens
		Matcher matcher = tokenPatterns.matcher(input);
		while (matcher.find())
		{
			if (matcher.group(TokenType.ALPHA.name()) != null)
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

	public static void main(String[] args) {
		String input = "lw x5, x0(x3)  # load word\nsub x5, x0, x3 ";

		// Create tokens and print them
		ArrayList<Token> tokens = lex(input);
		for (Token token : tokens)
			System.out.println(token);
	}
}
