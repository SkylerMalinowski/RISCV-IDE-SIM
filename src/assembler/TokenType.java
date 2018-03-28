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

/**
 * Defines lexeme in Regex notation to be used in conjunction with Token
 * @author Skyler Malinowski
 * @version February 2018
 */
public enum TokenType
{
	WHITESPACE("\\s+"),         // Spaces, tabs, and returns to be ignored
	COMMENT("#(.*)+"),          // User text to be ignored by assembler
	STRING("\"(.*)+\""),        // A special piece of text
	DIRECTIVE("\\.[a-zA-Z]+"),  // A type of system instruction
	NUMBER(
	        "([-+]?0[xX][0-9a-fA-F]*\\.?[0-9a-fA-F]+)|"  // Hexadecimal
	        + "([-+]?0[oO][0-7]*\\.?[0-7]+)|"            // Octal
	        + "([-+]?0[bB][0-1]*\\.?[0-1]+)|"            // Binary
	        + "([-+]?\\d*\\.?\\d+)"                      // Decimal
	        ),
	REGISTER(
	          "([xX]\\d+)|"                       // Integer
	        + "(zero)|(ra)|(sp)|(gp)|(tp)|(fp)|"  // Aliases (1/2)
	        + "([tsa][0-9]+)|"                    // Aliases (2/2)
	        + "([fF]\\d+)|"                       // Floating
	        + "(f[tsa][0-9]+)|"                   // Alias
	        + "(v[0-9]+)"                         // Vector
	        ),
	COMMA(","),               // Separator
	LeftParenthesis("\\("),   // Left Parenthesis
	RightParenthesis("\\)"),  // Right Parenthesis
	LABEL("[a-zA-Z0-9]+:"),      // Special type of word
	LITERAL("[\\w\\._]+"),    // Catch all for words
	//SYMBOL("\\W"),            // Catch all for symbols
	UNKNOWN("\\W+"),          // Everything else
	EOL("\r\n\f");
	
	public final String pattern;
	
	private TokenType(String pattern)
	{
		this.pattern = pattern;
	}

	@Override
	public String toString()
	{
		return this.name();
	}
}
