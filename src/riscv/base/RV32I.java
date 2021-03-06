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

import riscv.*;
import controller.*;
import simulator.LocationType;
import simulator.Simulator;
import simulator.StateNode;

import java.util.HashMap;

import javafx.collections.ObservableList;

/**
 * RV32I :: 32-Bit Integrated Base Instruction Set
 * Standard, Version: 2.0
 * @author Skyler Malinowski
 * @version April 2018
 */
public class RV32I {
	public final int xlen = 32;
	public HashMap<String, InstructionType> TypeMap = new HashMap<String, InstructionType>();
	
	/**
	 * Constructor
	 */
	public RV32I()
	{
		//TypeMap.put("LUI", InstructionType.U_Type);
		//TypeMap.put("AUIPC", InstructionType.U_Type);
		TypeMap.put("JAL", InstructionType.U_Type);
		
		//TypeMap.put("JALR", InstructionType.I_Type);
		
		TypeMap.put("BEQ", InstructionType.B_Type);
		TypeMap.put("BNE", InstructionType.B_Type);
		//TypeMap.put("BLT", InstructionType.B_Type);
		//TypeMap.put("BGE", InstructionType.B_Type);
		//TypeMap.put("BLTU", InstructionType.B_Type);
		//TypeMap.put("BGEU", InstructionType.B_Type);
		
		//TypeMap.put("LB", InstructionType.I_Type);
		//TypeMap.put("LH", InstructionType.I_Type);
		TypeMap.put("LW", InstructionType.I_Type);
		//TypeMap.put("LBU", InstructionType.I_Type);
		//TypeMap.put("LHU", InstructionType.I_Type);
		
		//TypeMap.put("SB", InstructionType.S_Type);
		//TypeMap.put("SH", InstructionType.S_Type);
		TypeMap.put("SW", InstructionType.S_Type);
		
		TypeMap.put("ADDI", InstructionType.I_Type);
		//TypeMap.put("SLTI", InstructionType.I_Type);
		//TypeMap.put("SLTIU", InstructionType.I_Type);
		//TypeMap.put("XORI", InstructionType.I_Type);
		//TypeMap.put("ORI", InstructionType.I_Type);
		//TypeMap.put("ANDI", InstructionType.I_Type);
		
		//TypeMap.put("SLLI", InstructionType.I_Type);
		//TypeMap.put("SRLI", InstructionType.I_Type);
		//TypeMap.put("SRAI", InstructionType.I_Type);
		
		TypeMap.put("ADD", InstructionType.R_Type);
		TypeMap.put("SUB", InstructionType.R_Type);
		//TypeMap.put("SLL", InstructionType.R_Type);
		//TypeMap.put("SLT", InstructionType.R_Type);
		//TypeMap.put("SLTU", InstructionType.R_Type);
		//TypeMap.put("XOR", InstructionType.R_Type);
		//TypeMap.put("SRL", InstructionType.R_Type);
		//TypeMap.put("SRA", InstructionType.R_Type);
		//TypeMap.put("OR", InstructionType.R_Type);
		//TypeMap.put("AND", InstructionType.R_Type);
		
		/* Unimplemented Instructions
		 * TypeMap.put("FENCE", InstructionType.);
		 * TypeMap.put("FENCE.I", InstructionType.);
		 * TypeMap.put("ECALL", InstructionType.);
		 * TypeMap.put("EBREAK", InstructionType.);
		 * TypeMap.put("CSRRWI", InstructionType.);
		 * TypeMap.put("CSRRSI", InstructionType.);
		 * TypeMap.put("CSRRCI", InstructionType.);
		 */
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
	public StateNode instructionCall(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock, 
			Program program, String instruction, String arg0, String arg1, String arg2)
	{
		int value;
		Memory memory = null;
		switch (instruction.toUpperCase())
		{
		// arg0 = arg1 + arg2
		case "ADD" :
			value = MemRegController.getIntRegister(intRegister, arg1) + MemRegController.getIntRegister(intRegister, arg2);
			// Check for overflow
			if (value % (Math.pow(2,this.xlen-1)-1) != 0)
			{
				value = value % (int)(Math.pow(2,this.xlen-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Register Overflow"));
			}
			MemRegController.setIntRegister(intRegister, arg0, value);
			return new StateNode(LocationType.INT_REG,arg0,""+MemRegController.getIntRegister(intRegister, arg0));
		
		// arg0 = arg1 + arg2
		case "ADDI" :
			value = Integer.parseInt(arg2, 10);
			// Check if proper width of 5
			if (value % (Math.pow(2,12-1)-1) != 0)
			{
				value = value % (int)(Math.pow(2,12-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Instruction Immediate Overflow"));
			}
			value += MemRegController.getIntRegister(intRegister, arg1);
			// Check for overflow
			if (value % (Math.pow(2,this.xlen-1)-1) != 0)
			{
				value = value % (int)(Math.pow(2,this.xlen-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Register Overflow"));
			}
			MemRegController.setIntRegister(intRegister, arg0, value);
			return new StateNode(LocationType.INT_REG,arg0,""+MemRegController.getIntRegister(intRegister, arg0));
		
		//sw arg0, arg1 ( arg2 )		sw destination, offest, (address
		// MEM[arg1+arg2]=arg0;	
		case "SW" :	 
			value = Integer.parseInt(MemRegController.getMemory(memoryBlock, arg1)) + Integer.parseInt(MemRegController.getMemory(memoryBlock, arg2));
			// Check for overflow
			if (value % (Math.pow(2,this.xlen-1)-1) != 0)
			{
				value = value % (int)(Math.pow(2,this.xlen-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Register Overflow"));
			}
			MemRegController.setMemory(memoryBlock, arg0, value);
			return new StateNode(LocationType.MEMORY,arg0,""+MemRegController.getIntRegister(intRegister, arg0));
		
		//lw arg0, arg1 ( arg2 )
		// arg0=MEM[arg1+arg2];	
		case "LW" :
			value = Integer.parseInt(MemRegController.getMemory(memoryBlock, arg1)) + Integer.parseInt(MemRegController.getMemory(memoryBlock, arg2));
			// Check for overflow
			if (value % (Math.pow(2,this.xlen-1)-1) != 0)
			{
				value = value % (int)(Math.pow(2,this.xlen-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Register Overflow"));
			}
			MemRegController.setIntRegister(intRegister, arg0, value);
			return new StateNode(LocationType.MEMORY,arg0,""+MemRegController.getIntRegister(intRegister, arg0));
		
		// if arg0 != arg1
			// j arg2
		case "BNE" :
			int temp1=MemRegController.getIntRegister(intRegister, arg1);
			int temp2=MemRegController.getIntRegister(intRegister, arg2);
			// Check for overflows
			if (temp1 % (Math.pow(2,this.xlen-1)-1) != 0 || temp2 % (Math.pow(2,this.xlen-1)-1) != 0)
			{
				temp1 = temp1 % (int)(Math.pow(2,this.xlen-1)-1);
				temp2 = temp2 % (int)(Math.pow(2,this.xlen-1)-1);
				// TODO :: Display to terminal instead
				program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Register Overflow"));
			}
			if (temp1 != temp2) {
				return new StateNode(LocationType.INT_REG,arg2,""+MemRegController.getIntRegister(intRegister, arg2));
				//Simulator.PC++;
			}
			
		// PC = offset + location of label
		case "JAL" :
			return new StateNode(LocationType.INT_REG,"PC","*value of new pc*");
		
		default :
			return null;
		}
	}
}
