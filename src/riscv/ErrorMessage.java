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

package riscv;

/**
 * A class to contain error reporting information
 * @author Skyler Malinowski
 * @version February 2018
 */
public class ErrorMessage
{
	private boolean isError;
	private int line = -1;
	private int index = -1;
	private String message = null;

	public static final boolean ERROR = true;
	public static final boolean WARNING = false;

	/**
	 * Constructor to generate an an error report
	 * @param isError
	 * @param message
	 */
	public ErrorMessage(boolean isError, String message)
	{
		this.isError = isError;
		this.message = message;
	}
	
	/**
	 * Constructor to generate an an error report
	 * @param isError
	 * @param line
	 * @param message
	 */
	public ErrorMessage(boolean isError, int line, String message)
	{
		this.isError = isError;
		this.line = line;
		this.message = message;
	}
	
	/**
	 * Constructor to generate an an error report
	 * @param isError
	 * @param line
	 * @param index
	 * @param message
	 */
	public ErrorMessage(boolean isError, int line, int index, String message)
	{
		this.isError = isError;
		this.line = line;
		this.index = index;
		this.message = message;
	}
	
	@Override
	public String toString()
	{
		String error;
		if (this.isError == true)
			error = "Error";
		else
			error = "Warning";
		
		if (this.line == -1 && this.index == -1)
			return error+": "+this.getMessage(); 
		if (this.line != -1 && this.index == -1)
			return error+": line "+this.getLine()+": "+this.getMessage();
		else
			return error+": line "+this.getLine()+" at index "+this.getIndex()+": "+this.getMessage();
	}
	
	/**
	 * Getter method for 'this.line'
	 * @return
	 */
	public int getLine()
	{
		return this.line;
	}
	
	/**
	 * Getter method for 'this.index'
	 * @return
	 */
	public int getIndex()
	{
		return this.index;
	}
	
	/**
	 * Getter method for 'this.message'
	 * @return
	 */
	public String getMessage()
	{
		return this.message;
	}
}
