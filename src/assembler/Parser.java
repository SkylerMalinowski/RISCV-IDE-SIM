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

/**
 * Checks program grammar with given bases and extensions
 * @author Skyler Malinowski
 * @version February 2018
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
		for (int i = 0; i < program.getTokenStream().size(); ++i)
		{
			//System.out.println(program.getTokenStream().get(i).getType());
			switch (program.getTokenStream().get(i).getType())
			{
			case EOL :
				// Ignore this Token Type
				break;
			case DIRECTIVE :
				i = lookupDirective(program, i);
				break;
			case LITERAL :
				i = lookupLiteral(program,i);
				break;
			case LABEL :
				break;
			default :
				program.appendErrorList(new ErrorMessage(ErrorMessage.ERROR, program.getTokenStream().get(i).getLine(), 
						program.getTokenStream().get(i).getIndexStart(), ""+program.getTokenStream().get(i)));
				break;
			}
		}
	}
	
	private int lookupDirective(Program program, int i)
	{
		return i;
	}

	private int lookupLiteral(Program program, int i)
	{
		ArrayList<Token> expectedTokens = riscv.lookupInstruction(program.getTokenStream().get(i));
		
		// If instruction does not exist find the next EOL token then progress like normal after error report
		if (expectedTokens.size() == 0)
		{
			program.appendErrorList(new ErrorMessage(ErrorMessage.ERROR,"Instruction does not exist."));
			while (program.getTokenStream().get(i).getType() != TokenType.EOL)
				++i;
		}
		else
		{
			for (; i < program.getTokenStream().size(); ++i)
			{
				if (program.getTokenStream().get(i).getType() == expectedTokens.get(0).getType())
				{
					expectedTokens.remove(0);
				}
				else
				{
					System.out.println("Error");
					
					while (program.getTokenStream().get(i).getType() != TokenType.EOL)
						++i;
				}
			} 
		}
		return i;
	}
}
