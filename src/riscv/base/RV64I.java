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

package riscv.base;

import riscv.Program;
import riscv.InstructionType;
import simulator.StateNode;

/**
 * RV64I :: 64-Bit Integrated Base Instruction Set
 * Standard, Version: 2.0
 * @author Skyler Malinowski
 * @version March 2018
 */
public class RV64I extends RV32I {
	public final int xlen = 64;
	
	/**
	 * Constructor
	 */
	public RV64I()
	{
		super();  // Parent constructor call
		
		TypeMap.put("LWU", InstructionType.I_Type);
		TypeMap.put("LD", InstructionType.I_Type);
		
		TypeMap.put("SD", InstructionType.S_Type);
		
		//TypeMap.put("SLLI", InstructionType.I_Type);
		//TypeMap.put("SRLI", InstructionType.I_Type);
		//TypeMap.put("SRAI", InstructionType.I_Type);
		
		TypeMap.put("ADDIW", InstructionType.I_Type);
		
		TypeMap.put("SLLIW", InstructionType.I_Type);
		TypeMap.put("SRLIW", InstructionType.I_Type);
		TypeMap.put("SRAIW", InstructionType.I_Type);
		
		TypeMap.put("ADDW", InstructionType.R_Type);
		TypeMap.put("SUBW", InstructionType.R_Type);
		TypeMap.put("SLLW", InstructionType.R_Type);
		TypeMap.put("SRLW", InstructionType.R_Type);
		TypeMap.put("SRAW", InstructionType.R_Type);
	}
	
	/**
	 * Carries out base specific instruction operations after warning checking
	 * @param program
	 * @param instruction
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public StateNode instructionCall(Program program, String instruction, String arg0, String arg1, String arg2)
	{
		switch (instruction.toUpperCase())
		{
		case "ADD" :
			// If (arguments have valid bit length)
			// - Alter register or memory value
			// Else
			// - Append warning to program class instance
			// Return location and value of where the instruction affected
			// - Register-Value; Memory-Value; PC-Value
			return new StateNode("*Location*","*Value*");
		case "SUB" :
			// same as above
			return new StateNode("*Location*","*Value*");
		case "ADDI" :
			// same as above
			return new StateNode("*Location*","*Value*");
		case "SW" :
			// same as above
			return new StateNode("*Location*","*Value*");
		case "LW" :
			// same as above
			return new StateNode("*Location*","*Value*");
		case "BNE" :
			// same as above
			return new StateNode("*Location*","*Value*");
		case "LUI" :
			// same as above
			return new StateNode("*Location*","*Value*");
		default :
			return null;
		}
	}
}
