/**
 * Copyright (C) 2018,  @authors
 * @author Skyler Malinowski  @email skyler.malinowski@gmail.com
 * @author Arjun Ohri         @email aohri@att.net
 * @author Alejandro Aguilar  @email alejandro.aguilar1195@gmail.com
 * @author Raj Balaji         @email nintedraj@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * GNU General Public License v3  
 * @link https://www.gnu.org/licenses/gpl.html
 */

package assembler;

import riscv.Program;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Tokenizes the string input
 * @author Skyler Malinowski
 * @version February 2018
 */

public class Lexer
{
	private Class base;
	private List extensions;
	
	/**
	 * Constructor saves current state of base and extensions
	 * @param base
	 * @param extensions
	 */
	public Lexer(Class base, List extensions)
	{
		setBase(base);
		setExtensions(extensions);
	}

	/**
	 * Setter method for 'this.base'
	 * @param base
	 */
	public void setBase(Class base)
	{
		this.base = base;
	}
		
	/**
	 * Setter method for 'this.base'
	 * @param extensions
	 */
	public void setExtensions(List extenions)
	{
		this.extensions = extensions;
	}

	public void lex(Program program)
	{
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		
		for (TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)),Pattern.CASE_INSENSITIVE);
		
		int i = 1;
		for (String line : program.getSource())
		{
			ArrayList<Token> tokens = new ArrayList<Token>();
			Matcher matcher = tokenPatterns.matcher(line);

			while (matcher.find())
			{
				if (matcher.group(TokenType.WHITESPACE.name()) != null)
				{
					continue;
				}
				else if (matcher.group(TokenType.COMMENT.name()) != null)
				{
					continue;
				}
				else if (matcher.group(TokenType.NUMBER.name()) != null)
				{
					tokens.add(new Token(TokenType.NUMBER,matcher.group(TokenType.NUMBER.name()),i,matcher.start(),matcher.end()));
					continue;
				}
				else if (matcher.group(TokenType.REGISTER.name()) != null)
				{
					tokens.add(new Token(TokenType.REGISTER,matcher.group(TokenType.REGISTER.name()),i,matcher.start(),matcher.end()));
					continue;
				}
				else if (matcher.group(TokenType.LITERAL.name()) != null)
				{
					tokens.add(new Token(TokenType.LITERAL,matcher.group(TokenType.LITERAL.name()),i,matcher.start(),matcher.end()));
					continue;
				}
				else if (matcher.group(TokenType.SYMBOL.name()) != null)
				{
					tokens.add(new Token(TokenType.SYMBOL,matcher.group(TokenType.SYMBOL.name()),i,matcher.start(),matcher.end()));
					continue;
				}
				else if (matcher.group(TokenType.DIRECTIVE.name()) != null)
				{
					tokens.add(new Token(TokenType.DIRECTIVE,matcher.group(TokenType.DIRECTIVE.name()),i,matcher.start(),matcher.end()));
					continue;
				}
				else
				{
					System.out.println("else");
				}
			}
			program.appendTokenList(tokens);
			i+=1;
		}
	}
}
