package riscv;

public class ErrorMessage
{
	private boolean isError;
	private int line;
	private int position;
	private String message;
	
	
	public static final boolean ERROR = true;
	public static final boolean WARNING = false;
	
	/**
	 * Constructor to generate an an error report
	 * @param isError
	 * @param line
	 * @param position
	 * @param message
	 */
	public void Message(boolean isError, int line, int position, String message)
	{
		this.isError = isError;
		this.line = line;
		this.position = position;
		this.message = message;
	}
	
	public void Message(String message)
	{
		this.isError = ERROR;
		this.message = message;
	}
	
	public boolean getIsError()
	{
		return this.isError;
	}
	
	public int getLine()
	{
		return this.line;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}
