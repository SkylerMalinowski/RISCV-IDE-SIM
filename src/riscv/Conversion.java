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
 * Static methods to convert numbers into different forms but still stored as Strings
 * @author Raj Balaji
 * @author Skyler Malinowski
 * @version April 2018
 */
public class Conversion {

	public static String ToBinary(String number, int width)
	{
		// Hexadecimal
		if (number.split("-")[1].startsWith("0x") || number.split("-")[1].startsWith("0X"))
		{
			return DecimalToBinary(""+Integer.parseInt(number.split("0[xX]")[1], 16), width);
		}
		// Octal
		else if (number.split("-")[1].startsWith("0o") || number.split("-")[1].startsWith("0O"))
		{
			return DecimalToBinary(""+Integer.parseInt(number.split("0[oO]")[1], 8), width);
		}
		// Binary
		else if (number.split("-")[1].startsWith("0b") || number.split("-")[1].startsWith("0B"))
		{
			if (number.charAt(0) == '-')
			{
				return DecimalToBinary("-"+Integer.parseInt(number.split("0[bB]")[1], 2), width);
			}
			else
			{
				return Integer.toBinaryString(Integer.parseInt(number.split("0[bB]")[1],2));
			}
		}
		else
		{
			return DecimalToBinary(""+Integer.parseInt(number, 10), width);
		}
	}

	public static String ASCIIToBinary(String string, int width) 	// convert a given string of ASCII characters into binary
	{
		String summation ="";
		int ASCII,sum;
		for(int i=0;i <string.length();i++)
		{
			summation = summation + DecimalToBinary(""+string.charAt(i), 8);	// binary string of 4 characters in this case
			break;
		}									
		return summation;			// returns a giant binary string of 32 characters ex: 0101010100101011010011
	}								

	

	public static String DecimalToBinary(String number, int width)
	{
		int padding;
		String Binary = "";
		String S = "";
		boolean negative = false, carry = false;
		int num = Integer.parseInt(number);
		
		if(number.charAt(0) == '-')
		{
			negative = true;
			number = number.substring(1);
		}
		
		// Handles overflow
		if (num % (int) (Math.pow(2, width-1) - 1) != 0)
		{
			num = num % (int) (Math.pow(2, width-1) - 1);
		}
		
		do
		{
			if((num%2)==0)
			{
				Binary += "0";
			}
			else
			{
				Binary += "1";
			}
			
			num /= 2;
		} while( num != 0 && Binary.length() < width - 1 );
		
		padding = width-Binary.length();	
		for (int i=0; i < padding; i++)
		{
			Binary += "0";	
		}
		if (negative == true)
		{
			for (int j=0; j < width; j++)
			{
				if(Binary.charAt(j)=='0')		// invert the bits
				{
					S += "1";
				}
				else if(Binary.charAt(j)=='1')	// invert the bits
				{
					S += "0";
				}
			}
			
			Binary = "";
			carry = true;
			for (int i=0; i < width; i++)				
			{		
				if (S.charAt(i) == '1' && carry == true)
				{
					Binary += "0";
					carry = true;			// there is still a carry on bit
				}
				else if (S.charAt(i) == '1'&& carry == false)
				{
					Binary += "1";
					carry = false;
				}
				else if (S.charAt(i) == '0'&& carry == true)
				{
					Binary += "1";
					carry = false;
				}
				else if (S.charAt(i) == '0' && carry == false)
				{
					Binary += "0";	
				}
			}
		}
		
		return new StringBuilder(Binary).reverse().toString();
	}
}
