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
 * Used by Lexer to classify and track text
 * @author Skyler Malinowski
 * @version March 2018
 */
public class InstructionToken
{
	private InstructionType type;
	private String instruction = null;
	private String destination = null;
	private String source1 = null;
	private String source2 = null;
	
	/**
	 * Constructor for R_Type, I_Type, S_Type, B_Type
	 * @param type
	 * @param i
	 * @param d
	 * @param s1
	 * @param s2
	 */
	public InstructionToken(InstructionType type, String i, String d, String s1, String s2)
	{
		this.type = type;
		this.instruction = i;
		this.destination = d;
		this.source1 = s1;
		this.source2 = s2;
		
		if (this.type == InstructionType.U_Type)
			System.out.println("Incorrect constructor used for U_Type.");
	}
	
	/**
	 * Constructor for U_Type
	 * @param type
	 * @param i
	 * @param d
	 * @param s1
	 */
	public InstructionToken(InstructionType type, String i, String d, String s1)
	{
		this.type = type;
		this.instruction = i;
		this.destination = d;
		this.source1 = s1;
		
		if (this.type != InstructionType.U_Type)
			System.out.println("Constructor to only be used for U_Type.");
	}
	
	/**
	 * Getter method for 'this.instruction'
	 * @return
	 */
	public String getInstruction()
	{
		return this.instruction;
	}
	
	/**
	 * Getter method for 'this.destination'
	 * @return
	 */
	public String getDestination()
	{
		return this.destination;
	}
	
	/**
	 * Getter method for 'this.source1'
	 * @return
	 */
	public String getSource1()
	{
		return this.source1;
	}
	
	/**
	 * Getter method for 'this.source2'
	 * @return
	 */
	public String getSource2()
	{
		return this.source2;
	}
	
	/**
	 * Displays Token information nicely
	 * @return
	 */
	@Override
	public String toString()
	{
		switch (this.type)
		{
		case R_Type :
		case I_Type :
		case B_Type :
			return String.format("%s %s, %s, %s", 
					this.instruction, 
					this.destination, 
					this.source1, 
					this.source2
					);
		
		case S_Type :
			return String.format("%s %s, %s(%s)", 
					this.instruction, 
					this.destination, 
					this.source1, 
					this.source2
					);
			
		case U_Type :
			return String.format("%s %s, %s", 
					this.instruction, 
					this.destination, 
					this.source1
					);
		
		default :
			String str = "";
			if (this.instruction != null)
				str = this.instruction;
			if (this.destination != null)
				str += " " + this.destination;
			if (this.source1 != null)
				str += ", " + this.source1;
			if (this.source2 != null)
				str += ", " + this.source2;
			return str;
		}
		
	}
}
