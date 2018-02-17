package assembler;

import riscv.Program;
import riscv.RISCV;

import java.util.List;

/**
 * Deals with interfacing Program with Lexer and Parser
 * 
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
		Lexer lexer = new Lexer(getBase(),getExtensions());
		//lexer(program);
		
		Parser parser = new Parser(getBase(),getExtensions());
		//parser(program);

		program.printErrorList();
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
