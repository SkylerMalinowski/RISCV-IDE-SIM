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

import riscv.RISCV;
import riscv.Program;

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
	private int PC;  // Pointer to LL elements
	
	/**
	 * Constructor
	 */
	public Simulator(RISCV riscv, Program program)
	{
		this.riscv = riscv;
		this.program = program;
		LL = new ArrayList<StateNode>();
		LL.add(new StateNode(null, null));
		PC = 0;
	}
	
	/**
	 * Handles the next instruction
	 * @param program
	 */
	public void step()
	{
		this.PC++;
		// Make Base or Extension function call
		// If (end of the LinkedList)
		// - Append LinkedList with node containing changed register location and value
		// Else
		// - Move forward a node in LinkedList
		// - Restore value
		// If (no more instructions)
		// - Deactivate step option
	}
	
	public void run()
	{
		/*
		while (this.PC <= program.getText().size())
		{
			this.step(this.program);
		}
		*/
	}
	
	/**
	 * Undo forward step
	 * @param program
	 */
	public void backstep()
	{
		this.PC--;
		// Move back one node in LinkedList
		// Restore value
		// If (at LinkedList head)
		// - Deactivate backstep option
	}
	
	
	public void reset()
	{
		/*
		while (this.LL.get(this.PC) != null)
		{
			this.backstep(this.program);
		}
		*/
	}
}
