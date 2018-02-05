package assembler;

import java.io.*;

public class Assembler
{
	private String[][] program;
	
	public static void main(String [] args)
	{
		String file = "temp.txt";
		Parser myInst = new Parser();
		myInst.LoadFile(file);
	}
}
