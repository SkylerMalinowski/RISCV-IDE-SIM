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

package riscv;

public class ErrorMessage
{
	private boolean isError;
	private int line;
	private int index;
	private String message;
	
	
	public static final boolean ERROR = true;
	public static final boolean WARNING = false;
	
	/**
	 * Constructor to generate an an error report
	 * @param isError
	 * @param line
	 * @param position
	 * @param message
	 */
	public void Message(boolean isError, int line, int index, String message)
	{
		this.isError = isError;
		this.line = line;
		this.index = index;
		this.message = message;
	}
	
	public void Message(String message)
	{
		this.isError = ERROR;
		this.message = message;
	}
	
	public boolean getIsError()
	{
		return this.isError;
	}
	
	public int getLine()
	{
		return this.line;
	}
	
	public int getPosition()
	{
		return this.index;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}
