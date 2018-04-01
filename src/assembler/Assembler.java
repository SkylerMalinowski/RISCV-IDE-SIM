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

package assembler;

import riscv.Program;
import riscv.RISCV;

/**
 * Deals with interfacing Program with Lexer and Parser
 * @author Skyler Malinowski
 * @version March 2018
 */
public class Assembler
{
	private RISCV riscv;
	
	/**
	 * Constructor saves state of RISCV in terms of current base and extensions
	 * @param riscv
	 */
	public Assembler(RISCV riscv)
	{
		this.riscv = riscv;
	}
	
	/**
	 * Method assembles the given program
	 * @param program
	 */
	public void assemble(Program program)
	{
		System.out.println("Assembler: assembling "+program.getFile()+"\n");
		
		Lexer lexer = new Lexer(this.riscv);
		lexer.lex(program);
		
		program.buildTokenStream();
		
		Parser parser = new Parser(this.riscv);
		parser.parse(program);
		
		program.buildText();
		program.buildData();
	}
}
