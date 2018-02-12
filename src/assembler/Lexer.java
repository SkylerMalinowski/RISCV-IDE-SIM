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
		// Token types and their patterns
		STRING("\"[A-Z|a-z|0-9| |`|~|!|@|$|%|^|&|*|(|)|-|_|=|+|[|]|{|}|\\\\|;|:|'|<|>|,|.|/|?]+\""),
		LABEL("[_|A-Z|a-z]+:"),
		REGISTER("(x|f|v)[0-9]+|(zero|ra|sp|gp|tp|fp)|(t|s|a)[0-9]+|(ft|fs|fa)[0-9]+"),
		DIRECTIVE("[.][A-Z|a-z|0-9]+"),
		COMMENT("#+[A-Z|a-z|0-9| |`|~|!|@|$|%|^|&|*|(|)|-|_|=|+|[|]|{|}|\\\\|;|:|'|\"|<|>|,|.|/|?]+"),
		ALPHA("-?[_|.|A-Z|a-z]+"),
		NUMERIC("0o-?[.|0-7]+|0(x|X)-?[.|A-F|a-f|0-9]+|-?[.|0-9]+"),  // Octal 0, Hex 0x, Deci nothing
		SYMBOL("[`|~|!|@|$|%|^|&|*|(|)|-|_|=|+|[|]|{|}|\\|;|:|'|\"|<|>|.|/|?]"),
		WHITESPACE("[ |\t|\f|\r|\n]+");
		
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

	public static ArrayList<Token> lex(String input)
	{
		// The tokens to return
		ArrayList<Token> tokens = new ArrayList<Token>();

		StringBuffer tokenPatternsBuffer = new StringBuffer();
		
		for (TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		// Token matching logic
		Matcher matcher = tokenPatterns.matcher(input);
		
		while (matcher.find())
		{
			if (matcher.group(TokenType.WHITESPACE.name()) != null)
				continue;
			
			else if (matcher.group(TokenType.LABEL.name()) != null)
				tokens.add(new Token(TokenType.LABEL, matcher.group(TokenType.LABEL.name())));
			
			else if (matcher.group(TokenType.ALPHA.name()) != null)
				tokens.add(new Token(TokenType.ALPHA, matcher.group(TokenType.ALPHA.name())));

			else if (matcher.group(TokenType.NUMERIC.name()) != null)
				tokens.add(new Token(TokenType.NUMERIC, matcher.group(TokenType.NUMERIC.name())));
			
			else if (matcher.group(TokenType.COMMENT.name()) != null)
				tokens.add(new Token(TokenType.COMMENT, matcher.group(TokenType.COMMENT.name())));
			
			else if (matcher.group(TokenType.REGISTER.name()) != null)
				tokens.add(new Token(TokenType.REGISTER, matcher.group(TokenType.REGISTER.name())));
			
			else if (matcher.group(TokenType.DIRECTIVE.name()) != null)
				tokens.add(new Token(TokenType.DIRECTIVE, matcher.group(TokenType.DIRECTIVE.name())));
			
			else if (matcher.group(TokenType.STRING.name()) != null)
				tokens.add(new Token(TokenType.STRING, matcher.group(TokenType.STRING.name())));
			
			else if (matcher.group(TokenType.SYMBOL.name()) != null)
				tokens.add(new Token(TokenType.SYMBOL, matcher.group(TokenType.SYMBOL.name())));
		}
		
		return tokens;
	}
}
