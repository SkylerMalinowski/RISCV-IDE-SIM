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

import riscv.base.*;
import riscv.extension.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import assembler.Token;
import assembler.TokenType;

/**
 * Dynamic class loader for user's current RISC-V architecture configuration
 * @author Skyler Malinowski
 * @version February 2018
 */
@SuppressWarnings("rawtypes")
public class RISCV
{
	private Class base;
	private List<Class> extensions;
	private HashMap<String,InstructionType> instructionMap;
	
	/**
	 * Constructor records users settings
	 * @param base
	 * @param exts
	 */
	public RISCV(String base, String[] exts)
	{
		this.setBase(base);
		this.setExtensions(exts);
		
		this.instructionMap = new HashMap<String,InstructionType>();
		this.buildInstructionMap();
	}

	/**
	 * Method gets 'this.base'
	 * @return this.base
	 */
	public Class getBase()
	{
		return this.base;
	}
	
	/**
	 * Method sets 'this.base' dynamically with class information
	 * @param base
	 */
	private void setBase(String base)
	{
		try
		{
			switch (base)
			{
			case "RV32I" :
				this.base = RV32I.class;
				break;
			case "RV64I" :
				this.base = RV64I.class;
				break;
			default :
				System.out.println("Internal Error: base not recognized or found.\n");
				break;
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method gets 'this.extensions'
	 * @return this.extensions
	 */
	public List getExtensions()
	{
		return this.extensions;
	}
	
	/**
	 * Method sets 'this.extensions' dynamically with class information
	 * @param exts
	 */
	private void setExtensions(String[] exts)
	{
		this.extensions = new ArrayList<Class>();
		
		try
		{
			for (String ext : exts)
			{
				switch (ext)
				{
				case "M" :
					this.extensions.add(M.class);
					break;
				case "A" :
					this.extensions.add(A.class);
					break;
				case "F" :
					this.extensions.add(F.class);
					break;
				case "D" :
					this.extensions.add(D.class);
					break;
				default :
					System.out.println("Internal Error: \'"+ext+"\' is not a recognized extension.\n");
					System.out.println("Internal Recovery: \'"+ext+"\' not added to dynamic extensions list.\n");
					break;
				}
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a unified hashmap of valid directives and instructions for parsing
	 */
	private void buildInstructionMap()
	{
		if (this.base == riscv.base.RV32I.class)
		{
			this.instructionMap.putAll(new RV32I().TypeMap);
		}
		else if (this.base == riscv.base.RV64I.class)
		{
			this.instructionMap.putAll(new RV64I().TypeMap);
		}
		else
		{
			System.out.println("Not a recognized base");
		}
		
		for (Object ext : this.extensions)
		{
			if (ext == riscv.extension.A.class)
			{
				this.instructionMap.putAll(new M().TypeMap);
			}
			else if (ext == riscv.extension.D.class)
			{
				this.instructionMap.putAll(new M().TypeMap);
			}
			else if (ext == riscv.extension.F.class)
			{
				this.instructionMap.putAll(new M().TypeMap);
			}
			else if (ext == riscv.extension.M.class)
			{
				this.instructionMap.putAll(new M().TypeMap);
			}
			else
			{
				System.out.println("Other extension");
			}
		}
	}
	
	/**
	 * Used by parser to verify instruction and know what tokens are expected next in the token stream
	 * @param token
	 */
	public ArrayList<Token> lookupInstruction(Token token)
	{
		ArrayList<Token> expectedTokens = new ArrayList<Token>();
		if (this.instructionMap.get(token.getData().toUpperCase()) != null)
		{
			expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
			expectedTokens.add(new Token(TokenType.COMMA, null, 0, 0, 0));
			
			switch (this.instructionMap.get(token.getData().toUpperCase()))
			{
			case R_Type :
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.COMMA, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				break;
			case I_Type :
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.COMMA, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.NUMBER, null, 0, 0, 0));
				break;
			case S_Type :
				expectedTokens.add(new Token(TokenType.NUMBER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.LeftParenthesis, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.RightParenthesis, null, 0, 0, 0));
				break;
			case B_Type :
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.COMMA, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.LABEL, null, 0, 0, 0));
				break;
			case U_Type :
				expectedTokens.add(new Token(TokenType.NUMBER, null, 0, 0, 0));
				break;
			default :
				System.out.println("System Internal Error: Token "+this.instructionMap.get(token.getData().toUpperCase())+" not handled.");
				break;
			}
		}
		else
		{
			expectedTokens = new ArrayList<Token>();
		}
		return expectedTokens;
	}
	
	/**
	 * Used by parser to verify directive and know what tokens are expected next in the token stream
	 * @param token
	 */
	public ArrayList<Token> lookupDirective(Token token)
	{
		ArrayList<Token> expected = new ArrayList<Token>();
		switch (token.getData().toUpperCase())
		{
		case ".text" :
			// expect <NUMERIC> or nothing
			break;
		case ".data" :
			// expect <NUMERIC> or nothing
			break;
		case ".string" :
		case ".asciiz" :
			// expect: <LITERAL>|<VARIABLE> <STRING>
		default :
			break;
		}
		return expected;
	}
	
	// Debugging
	public static void main(String[] args)
	{
		// Dynamic Class and class method call
		/*
		try
		{
			Class baseClass = RV32I.class;
			RV32I rv32i = (RV32I) baseClass.getConstructor().newInstance();
			//rv32i.main();
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		*/

		String[] str = {"M","A","F","D"};
		RISCV riscv = new RISCV("RV64I",str);
		riscv.lookupInstruction(new Token(null, "ADD", 0, 0, 0));
		riscv.lookupInstruction(new Token(null, "LD", 0, 0, 0));
	}
}
