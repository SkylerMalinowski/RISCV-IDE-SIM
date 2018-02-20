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

import java.util.ArrayList;
import java.util.List;

/**
 * Deals with interfacing Program with Lexer and Parser
 * @author Skyler Malinowski
 * @version February 2018
 */

public class Assembler
{
	private Class base;
	private List extensions;
	
	/**
	 * Constructor saves state of RISCV in terms of current base and extensions
	 * @param riscv
	 */
	public Assembler(RISCV riscv)
	{
		setBase(riscv.getBase());
		setExtensions(riscv.getExtensions());
	}
	
	/**
	 * Method assembles the given program
	 * @param program
	 */
	public void assemble(Program program)
	{
		/*
		lexer.lex(program);
		
		for (ArrayList<Token> list : program.getTokenList())
		{
			System.out.println(list);
		}
		
		//Parser parser = new Parser(getBase(),getExtensions());

		program.printErrorList();
		*/
	}
	
	/**
	 * Getter method for 'this.base'
	 * @return this.base
	 */
	public Class getBase()
	{
		return this.base;
	}
	
	/**
	 * Setter method for 'this.base'
	 * @param base
	 */
	public void setBase(Class base)
	{
		this.base = base;
	}
	
	/**
	 * Getter method for 'this.extetnsions'
	 * @return this.extensions
	 */
	public List getExtensions()
	{
		return this.extensions;
	}
	
	/**
	 * Setter method for 'this.base'
	 * @param extensions
	 */
	public void setExtensions(List extenions)
	{
		this.extensions = extensions;
	}
	
	// Debugging
	public static void main(String[] args)
	{
		String filePath = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\test.asm";
		RISCV riscv = new RISCV();
		Program program = new Program(filePath);
		Assembler assembler = new Assembler(riscv);
		assembler.assemble(program);
	}
}
