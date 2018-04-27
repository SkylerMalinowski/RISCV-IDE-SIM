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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import assembler.Token;
import assembler.TokenType;

/**
 * Dynamic class loader for user's current RISC-V architecture configuration
 * @author Skyler Malinowski
 * @version March 2018
 */
@SuppressWarnings("rawtypes")
public class RISCV
{
	private Class base;
	private ArrayList<Class> extensions;
	private HashMap<String,InstructionType> instructionMap;
	
	/**
	 * Constructor
	 * @param base
	 * @param exts
	 */
	public RISCV(String base, ArrayList<String> exts)
	{
		this.setBase(base);
		this.setExtensions(exts);
		
		this.instructionMap = new HashMap<String,InstructionType>();
		this.buildInstructionMap();
	}
	
	/**
	 * Constructor
	 * @param base
	 */
	public RISCV(String base)
	{
		this.setBase(base);
		
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
			switch (base.toUpperCase())
			{
			case "RV32I" :
				this.base = RV32I.class;
				break;
			case "RV64I" :
				this.base = RV64I.class;
				break;
			default :
				System.out.println("Internal Error: base not recognized or found.\n");
				System.exit(1);
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
	private void setExtensions(ArrayList<String> exts)
	{
		this.extensions = new ArrayList<Class>();
		
		try
		{
			for (String ext : exts)
			{
				switch (ext.toUpperCase())
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
		
		if (this.extensions != null)
		{
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
			
			switch (this.instructionMap.get(token.getData().toUpperCase()))
			{
			case R_Type :
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
				break;
			case I_Type :
				expectedTokens.add(new Token(TokenType.REGISTER, null, 0, 0, 0));
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
				expectedTokens.add(new Token(TokenType.LITERAL, null, 0, 0, 0));
				break;
			case U_Type :
				expectedTokens.add(new Token(TokenType.LITERAL, null, 0, 0, 0));
				break;
			default :
				System.out.println("System Internal Error: Token "+this.instructionMap.get(token.getData().toUpperCase())+" not handled.");
				System.exit(1);
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
		case ".TEXT" :  // read-only section containing executable code
			expected.add(new Token(TokenType.NUMBER, "?", 0, 0, 0));
			break;
		case ".DATA" :  // read-write section containing global or static variables
			expected.add(new Token(TokenType.NUMBER, "?", 0, 0, 0));
			break;
		/*
		case ".RODATA" :  // read-only section containing constant variables
			break;
		case ".BSS" :  // read-write section containing uninitialised data
			break;
		*/
			
		case ".2BYTE" :
		case ".4BYTE" :
		case ".8BYTE" :
		case ".HALF" :
		case ".WORD" :
		case ".DWORD" :
			expected.add(new Token(TokenType.NUMBER, "+", 0, 0, 0));
			break;

		case ".ASCIIZ" :
		case ".STRING" :
			expected.add(new Token(TokenType.STRING, null, 0, 0, 0));
			break;
		case ".EQU" :
		case ".BYTE" :
			expected.add(new Token(TokenType.NUMBER, null, 0, 0, 0));
			break;
		default :
			System.out.println("System Internal Error: Directive not handled.");
			System.exit(1);
			break;
		}
		
		return expected;
	}
	
	/**
	 * Determines the InstructionType of the given token
	 * @param token
	 * @return
	 */
	public InstructionType typeLookup(Token token)
	{
		return this.instructionMap.get(token.getData().toUpperCase());
	}
}
