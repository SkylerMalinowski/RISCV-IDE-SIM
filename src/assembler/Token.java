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
 * Used by Lexer to classify and track text
 * @author Skyler Malinowski
 * @version February 2018
 */
public class Token
{
	private TokenType type;
	private String data;
	private int line;
	private int index_start;
	private int index_end;

	public Token(TokenType type, String data, int line, int index_start, int index_end)
	{
		this.type = type;
		this.data = data;
		this.line = line;
		this.index_start = index_start;
		this.index_end = index_end;
	}

	/**
	 * Getter method for 'this.type'
	 * @return String
	 */
	public TokenType getType()
	{
		return this.type;
	}
	/**
	 * Getter method for 'this.data'
	 * @return String
	 */
	public String getData()
	{
		return this.data;
	}

	/**
	 * Getter method for 'this.line'
	 * @return int
	 */
	public int getLine()
	{
		return this.line;
	}

	/**
	 * Getter method for 'this.index_start'
	 * @return int
	 */
	public int getIndexStart()
	{
		return this.index_start;
	}

	/**
	 * Getter method for 'this.index_end'
	 * @return int
	 */
	public int getIndexEnd()
	{
		return this.index_end;
	}

	@Override
	public String toString()
	{
		return String.format("<%s :: %s>", type.name(), data);
	}
}
