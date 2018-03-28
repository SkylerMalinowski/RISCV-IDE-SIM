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

package riscv.extension;

import riscv.InstructionType;
import riscv.Program;

import java.util.HashMap;

/**
 * M :: Integer Multiplication & Division
 * Standard, Version: 2.0
 * @author Skyler Malinowski
 * @version February 2018
 */
public class M
{
	public HashMap<String, InstructionType> TypeMap;
	
	/**
	 * Constructor
	 */
	public M()
	{
		TypeMap = new HashMap<String, InstructionType>();
		
		// Append HashMap with instructions as specified in RISC-V handbook
	}
	
	/**
	 * Carries out extension specific instruction operations after warning checking
	 * @param program
	 * @param instruction
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public boolean instructionCall(Program program, String instruction, String arg0, String arg1, String arg2)
	{
		switch (instruction.toUpperCase())
		{
		case "ADD" :
			// If (arguments have valid bit length)
			// - Alter register or memory value
			// Else
			// - Append warning to program class instance
			return true;
		default :
			return false;
		}
	}
}
