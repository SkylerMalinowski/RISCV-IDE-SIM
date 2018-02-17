package assembler;

import java.util.List;

public class Parser
{
	private Class base;
	private List extensions;
	
	/**
	 * Constructor saves current state of base and extensions
	 * @param base
	 * @param extensions
	 */
	public Parser(Class base, List extensions)
	{
		this.base = base;
		this.extensions = extensions;
	}
}
