package simulator;

import riscv.RISCV;

import java.util.List;

/**
 * Deals with translating the Program into actions
 * 
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
