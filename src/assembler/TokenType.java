/**
 * Copyright (C) 2018,  @authors
 * @author Skyler Malinowski  @email skyler.malinowski@gmail.com
 * @author Arjun Ohri         @email aorhi@att.net
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

enum TokenType
{
	// Regex Notation
	WHITESPACE("\\s+"),
	COMMENT("#(.*)+"),
	NUMBER("([\\d]+[\\.]?[\\d]+) | (0[x|X][\\da-fA-F]+[\\.]?[\\da-fA-F]+)"),
	REGISTER("([xXfFvV][\\d]+)|(zero)"),
	SYMBOL("[,]"),
	LITERAL("[\\w\\d\\._]+");
	
	public final String pattern;
	
	private TokenType(String pattern)
	{
		this.pattern = pattern;
	}
}
