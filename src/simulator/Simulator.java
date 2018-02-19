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

package simulator;

import riscv.RISCV;

import java.util.List;

/**
 * Deals with translating the Program into actions
 * @author Skyler Malinowski
 * @version February 2018
 */

public class Simulator
{
	private Class base;
	private List extensions;
	
	/**
	 * Constructor saves state of RISCV in terms of current base and extensions
	 * @param riscv
	 */
	public Simulator(RISCV riscv)
	{
		setBase(riscv.getBase());
		setExtensions(riscv.getExtensions());
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
}
