/**
 * Copyright (C) 2018,  @authors
 * @author Skyler Malinowski  @email skyler.malinowski@gmail.com
 * @author Arjun Ohri         @email aorhi@att.net
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
import java.util.List;

/**
 * Dynamic class loader for user's current RISC-V architecture configuration
 * 
 * @author Skyler Malinowski
 * @version February 2018
 */

public class RISCV
{
	private Class base;
	private List extensions;
	
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
	public void setBase(String base)
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
	public void setExtensions(String[] exts)
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
					this.extensions.add(M.class);
					break;
				case "D" :
					this.extensions.add(D.class);
					break;
				}
			}
		}
		catch (IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
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

		String[] str = {"M","F"};
		RISCV riscv = new RISCV();
		
		riscv.setBase("RV32I");
		riscv.setExtensions(str);
	}
}
