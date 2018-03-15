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

import riscv.ErrorMessage;
import riscv.Program;
import riscv.RISCV;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Checks program grammar with given bases and extensions
 * @author Skyler Malinowski
 * @version March 2018
 */
public class Parser
{
	RISCV riscv;
  
	/**
	 * Constructor saves current state of base and extensions
	 * @param base
	 * @param extensions
	 */
	public Parser(RISCV riscv)
	{
		this.riscv = riscv;
	}
	
	/**
	 * Grammar checks program after Lexing
	 * @param program
	 */
	public void parse(Program program)
	{
		while (program.getTokenStream().size() > 0)
		{
			switch (program.getTokenStream().get(0).getType())
			{
			case EOL :
				program.getTokenStream().remove(0);
				break;
			case DIRECTIVE :
				lookupDirective(program);
				break;
			case LITERAL :
				lookupLiteral(program);
				break;
			case LABEL :
				lookupLabel(program);
				break;
			default :
				program.appendErrorList(new ErrorMessage(
						ErrorMessage.ERROR, 
						program.getTokenStream().get(0).getLine(), 
						program.getTokenStream().get(0).getIndexStart(), 
						"Token '"+program.getTokenStream().get(0)+"' should not be there."
						));
				
				errorRecovery(program);
				break;
			}
		}
	}

	/**
	 * Verifies if the literal instruction is valid and if the arguments take the correct form
	 * @param program
	 */
	private void lookupLiteral(Program program)
	{
		ArrayList<Token> expectedTokens = riscv.lookupInstruction(program.getTokenStream().get(0));
		
		if (expectedTokens.size() > 0)
		{
			// Strip off valid token from stream
			program.getTokenStream().remove(0);
			
			while (program.getTokenStream().size() > 0 && expectedTokens.size() > 0)
			{
				if (program.getTokenStream().get(0).getType() == expectedTokens.get(0).getType())
				{
					// Consume the matching tokens
					program.getTokenStream().remove(0);
					expectedTokens.remove(0);
				}
				else if (program.getTokenStream().get(0).getType() == TokenType.EOL 
						&& program.getTokenStream().get(1).getType() == expectedTokens.get(0).getType())
				{
					program.appendWarningList(new ErrorMessage(
							ErrorMessage.WARNING, 
							program.getTokenStream().get(1).getLine(), 
							"Instruction is split over multiple lines."
							));
					
					program.getTokenStream().remove(0);
				}
				else
				{
					program.appendErrorList(new ErrorMessage(
							ErrorMessage.ERROR, 
							program.getTokenStream().get(0).getLine(), 
							"Instruction does take the proper form."
							));
					
					expectedTokens = new ArrayList<Token>();
					
					errorRecovery(program);
				}
			}
		}
		else
		{
			program.appendErrorList(new ErrorMessage(
					ErrorMessage.ERROR, 
					program.getTokenStream().get(0).getLine(), 
					"Instruction does not exist."
					));
			
			errorRecovery(program);
		}
	}
	
	/**
	 * Verifies if directive is valid and if the arguments take the correct form
	 * @param program
	 */
	private void lookupDirective(Program program)
	{
		ArrayList<Token> expectedTokens = riscv.lookupDirective(program.getTokenStream().get(0));
		
		if (expectedTokens.size() > 0)
		{
			// Strip off valid token from stream
			program.getTokenStream().remove(0);
			
			while (program.getTokenStream().size() > 0 && expectedTokens.size() > 0)
			{
				if (expectedTokens.get(0).getData() == null)
				{
					if (program.getTokenStream().get(0).getType() == expectedTokens.get(0).getType())
					{
						program.getTokenStream().remove(0);
						expectedTokens = new ArrayList<Token>();
					}
					else
					{
						program.appendErrorList(new ErrorMessage(
								ErrorMessage.ERROR, 
								program.getTokenStream().get(0).getLine(), 
								"Directive does take the proper form."
								));
						
						expectedTokens = new ArrayList<Token>();
						
						errorRecovery(program);
					}
				}
				else
				{
					switch (expectedTokens.get(0).getData())
					{
					case "?" :  // zero or one
						if (program.getTokenStream().get(0).getType() == expectedTokens.get(0).getType())
						{
							program.getTokenStream().remove(0);
							expectedTokens = new ArrayList<Token>();
						}
						else
						{
							expectedTokens = new ArrayList<Token>();
						}
						break;
					
					case "+" :  // one plus
						if (program.getTokenStream().get(0).getType() == expectedTokens.get(0).getType())
						{
							program.getTokenStream().remove(0);
							
							if (program.getTokenStream().get(0).getType() != expectedTokens.get(0).getType())
								expectedTokens = new ArrayList<Token>();
						}
						else
						{
							program.appendErrorList(new ErrorMessage(
									ErrorMessage.ERROR, 
									program.getTokenStream().get(0).getLine(), 
									"Directive does take the proper form."
									));
							
							expectedTokens = new ArrayList<Token>();
						}
						break;
					
					default :
						break;
					}
				}
			}
		}
		else
		{
			program.appendErrorList(new ErrorMessage(
					ErrorMessage.ERROR, 
					program.getTokenStream().get(0).getLine(), 
					"Directive does not exist."
					));
			
			errorRecovery(program);
		}
	}
	
	/**
	 * Determines if label is valid and has not been used
	 * @param program
	 */
	private void lookupLabel(Program program)
	{
		// Check if label name has been used
		if (program.labelUsed(program.getTokenStream().get(0)))
		{
			program.appendErrorList(new ErrorMessage(
					ErrorMessage.ERROR, 
					program.getTokenStream().get(0).getLine(), 
					"Label already used."
					));
			
			errorRecovery(program);
		}
		else
		{
			program.appendLabelList(program.getTokenStream().get(0));
			program.getTokenStream().remove(0);
		}
		
		if (Objects.equals(program.getTokenStream().get(0).getData(), ".data")
				|| Objects.equals(program.getTokenStream().get(0).getData(), ".text"))
		{
			program.appendErrorList(new ErrorMessage(
					ErrorMessage.ERROR, 
					program.getTokenStream().get(0).getLine(), 
					"Label cannot precede directive."
					));
			
			errorRecovery(program);
		}
	}
	
	/**
	 * Recover from parse error by finding an EOL Token
	 * @param program
	 */
	private void errorRecovery(Program program)
	{
		// Consume Tokens until EOL Token is reached
		while (program.getTokenStream().get(0).getType() != TokenType.EOL)
			program.getTokenStream().remove(0);
		
		// Consume EOL Token
		program.getTokenStream().remove(0);
	}
}
