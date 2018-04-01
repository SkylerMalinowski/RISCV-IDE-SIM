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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Class to handle simulation of assembly code.
 * @author Skyler Malinowski
 * @version March 2018
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
	 */
	public Simulator(RISCV riscv, Program program)
	{
		this.riscv = riscv;
		this.program = program;
		LL = new ArrayList<StateNode>();
		LL.add(new StateNode(null, null));  // Head node
		PC = -1;
		i = 0;
	}
	
	/**
	 * Handles the next instruction
	 * @param program
	 */
	public void step()
	{
		this.PC++;
		
		boolean madeCall = false;
		StateNode node = null;
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		if (this.program.getTextList().get(PC).size() > 0)
		{
			tokens = this.program.getTextList().get(PC);
			while (tokens.size() > 0 && tokens.get(0).getType() != TokenType.LITERAL)
			{
				tokens.remove(0);
			}
			
			if (tokens.size() > 0)
			{
				dynamicCall(tokens);
				madeCall = true;
			}
			else
			{
				this.step();
			}
		}
		
		if (madeCall)
		{
			if (this.LL.size() == i + 1)
			{
				this.LL.add(node);
			}
			else
			{
				this.i++;
				this.restore();
			}
		}
	}
	
	/**
	 * Steps through until program completion
	 */
	public void run()
	{
		while (this.PC < program.getTextList().size())
		{
			this.step();
		}
	}
	
	/**
	 * Undo forward step
	 * @param program
	 */
	public void backStep()
	{
		if (this.LL.get(i).getLocation() != null 
				&& this.LL.get(i).getValue() != null)
		{
			this.i--;
		}
		
		this.restore();
		
		this.PC--;
		
	}
	
	/**
	 * Returns simulator to initial state
	 */
	public void reset()
	{
		while (this.LL.get(this.PC) != null)
		{
			backStep();
		}
	}

	/**
	 * Dynamically calls function in certain classes during runtime
	 */
	@SuppressWarnings("unchecked")
	private StateNode dynamicCall(ArrayList<Token> tokens)
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
					return rv32i.instructionCall(this.program, 
							tokens.get(0).getData(), 
							tokens.get(1).getData(), 
							tokens.get(2).getData(), 
							tokens.get(3).getData());
				case U_Type :
					return rv32i.instructionCall(this.program, 
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
	
	/**
	 * Undo instruction
	 */
	private void restore()
	{
		// LL.get(i).getLocation()
		// LL.get(i).getValue()
	}
	
	public static void main(String[] args)
	{
		RISCV r = new RISCV("RV32I");
		
		String f = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\test.asm";
		Program p = new Program(new File(f));
		
		Assembler a = new Assembler(r);
		a.assemble(p);
		
		Simulator s = new Simulator(r, p);
		s.step();
	}
}
