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

package simulator;

import riscv.*;
import riscv.base.*;
import riscv.extension.*;
import assembler.Assembler;
import assembler.Token;
import assembler.TokenType;
import controller.FloatRegisters;
import controller.IntRegisters;
import controller.MemRegController;
import controller.Memory;
import javafx.collections.ObservableList;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Class to handle simulation of assembly code.
 * @author Skyler Malinowski
 * @version April 2018
 */
public class Simulator
{
	private RISCV riscv;
	private Program program;
	private ArrayList<StateNode> LL;  // Stores hardware state after instruction
	private int PC;  // Pointer to line in code
	private int i;  // Pointer to LL elements
	
	/**
	 * Constructor
	 * @param riscv
	 * @param program
	 */
	public Simulator(RISCV riscv, Program program, ObservableList<Memory> memoryBlock)
	{
		this.riscv = riscv;
		this.program = program;
		LL = new ArrayList<StateNode>();
		LL.add(new StateNode(null, null, null));  // Head node
		PC = -1;
		i = 0;
		
		initializeMemory(memoryBlock);
	}
	
	/**
	 * Loads memory with information from DATA region
	 * @param memoryBlock
	 */
	private void initializeMemory(ObservableList<Memory> memoryBlock)
	{
		for (ArrayList<Token> tokens : program.getDataList())
		{
			for (Token token : tokens)
			{
				switch (token.getType())
				{
				case STRING :
					// Load string into adjacent vacant memory
					break;
					
				case NUMBER :
					// Load number into adjacent vacant memory
					break;
					
				default :
					break;
				}
			}
		}
	}

	/**
	 * Handles the next instruction
	 * @param memoryBlock 
	 * @param floatRegister 
	 * @param intRegister 
	 * @param program
	 */
	public void step(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock)
	{
		this.PC++;
		
		boolean madeCall = false;
		StateNode node = null;
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		if (this.program.getTextList().get(PC).size() > 0)
		{
			tokens = this.program.getTextList().get(PC);
			
			// Strips non-instruction tokens
			while (tokens.size() > 0 
					&& tokens.get(0).getType() != TokenType.LITERAL)
			{
				tokens.remove(0);
			}
			
			// If tokens are left
			if (tokens.size() > 0)
			{
				node = dynamicCall(intRegister, floatRegister, memoryBlock, tokens);
				if (node != null)
				{
					madeCall = true;
				}
			}
			else
			{
				this.step(intRegister, floatRegister, memoryBlock);
			}
		}
		
		if (madeCall)
		{
			// If at the tail of LL, then append node
			if (this.LL.size() == i + 1)
			{
				this.LL.add(node);
			}
			// Else increment LL pinter, i, and restore data from LL
			else
			{
				this.i++;
				this.restore(intRegister, floatRegister, memoryBlock);
			}
		}
	}
	
	/**
	 * Steps through until program completion
	 * @param intRegister
	 * @param floatRegister
	 * @param memoryBlock
	 */
	public void run(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock)
	{
		while (this.PC < program.getTextList().size())
		{
			this.step(intRegister, floatRegister, memoryBlock);
		}
	}
	
	/**
	 * Undo forward step
	 * @param intRegister
	 * @param floatRegister
	 * @param memoryBlock
	 */
	public void backStep(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock)
	{
		if (this.LL.get(i).getLocation() != null 
				&& this.LL.get(i).getValue() != null)
		{
			this.i--;
		}
		
		this.restore(intRegister, floatRegister, memoryBlock);
		
		this.PC--;
		
	}
	
	/**
	 * Returns simulator to initial state
	 * @param intRegister
	 * @param floatRegister
	 * @param memoryBlock
	 */
	public void reset(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock)
	{
		while (this.LL.get(this.PC) != null)
		{
			backStep(intRegister, floatRegister, memoryBlock);
		}
	}

	/**
	 * Undo instruction
	 * @param intRegister
	 * @param floatRegister
	 * @param memoryBlock
	 */
	private void restore(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock)
	{
		switch (LL.get(i).getType())
		{
		case INT_REG :
			MemRegController.setIntRegister(intRegister, LL.get(i).getLocation(), Integer.parseInt(LL.get(i).getValue()));
			break;
		
		case FLOAT_REG :
			MemRegController.setFloatRegister(floatRegister, LL.get(i).getLocation(), Integer.parseInt(LL.get(i).getValue()));
			break;
		
		case MEMORY :
			MemRegController.setMemory(memoryBlock, LL.get(i).getLocation(), Integer.parseInt(LL.get(i).getValue()));
			break;
		
		default :
			break;
		}
	}

	/**
	 * Dynamically calls function in certain classes during runtime
	 * @param intRegister
	 * @param floatRegister
	 * @param memoryBlock
	 * @param tokens
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private StateNode dynamicCall(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock, ArrayList<Token> tokens)
	{
		try
		{
			if (this.riscv.getBase() == RV32I.class)
			{
				RV32I rv32i = new RV32I();
				
				switch (this.riscv.typeLookup(tokens.get(0)))
				{
				case R_Type :
				case I_Type :
				case S_Type :
				case B_Type :
					return rv32i.instructionCall(
							intRegister,
							floatRegister,
							memoryBlock,
							this.program, 
							tokens.get(0).getData(), 
							tokens.get(1).getData(), 
							tokens.get(2).getData(), 
							tokens.get(3).getData());
				case U_Type :
					return rv32i.instructionCall(
							intRegister,
							floatRegister,
							memoryBlock,
							this.program, 
							tokens.get(0).getData(), 
							tokens.get(1).getData(), 
							tokens.get(2).getData(), 
							"");
				default :
					return null;
				}
					
			}
			else if (this.riscv.getBase() == RV64I.class)
			{
				RV64I rv64i = (RV64I) this.riscv.getBase().getConstructor().newInstance();
				
				switch (this.riscv.typeLookup(tokens.get(0)))
				{
				case R_Type :
				case I_Type :
				case S_Type :
				case B_Type :
					return rv64i.instructionCall(this.program, 
							tokens.get(0).getData(), 
							tokens.get(1).getData(), 
							tokens.get(2).getData(), 
							tokens.get(3).getData());
				case U_Type :
					return rv64i.instructionCall(this.program, 
							tokens.get(0).getData(), 
							tokens.get(1).getData(), 
							tokens.get(2).getData(), 
							"");
				default :
					return null;
				}
			}
			
			if (this.riscv.getExtensions() != null)
			{
				for (Object obj : this.riscv.getExtensions())
				{
					if (obj.getClass() == A.class)
					{
						M ext = M.class.getConstructor().newInstance();
						
						switch (this.riscv.typeLookup(tokens.get(0)))
						{
						case R_Type :
						case I_Type :
						case S_Type :
						case B_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									tokens.get(3).getData());
						case U_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									"");
						default :
							return null;
						}
					}
					else if (obj.getClass() == A.class)
					{
						A ext = A.class.getConstructor().newInstance();
						
						switch (this.riscv.typeLookup(tokens.get(0)))
						{
						case R_Type :
						case I_Type :
						case S_Type :
						case B_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									tokens.get(3).getData());
						case U_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									"");
						default :
							return null;
						}
					}
					else if (obj.getClass() == F.class)
					{
						F ext = F.class.getConstructor().newInstance();
						
						switch (this.riscv.typeLookup(tokens.get(0)))
						{
						case R_Type :
						case I_Type :
						case S_Type :
						case B_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									tokens.get(3).getData());
						case U_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									"");
						default :
							return null;
						}
					}
					else if (obj.getClass() == D.class)
					{
					D ext = D.class.getConstructor().newInstance();
						
						switch (this.riscv.typeLookup(tokens.get(0)))
						{
						case R_Type :
						case I_Type :
						case S_Type :
						case B_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									tokens.get(3).getData());
						case U_Type :
							return ext.instructionCall(this.program, 
									tokens.get(0).getData(), 
									tokens.get(1).getData(), 
									tokens.get(2).getData(), 
									"");
						default :
							return null;
						}
					}
				}
			}
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
