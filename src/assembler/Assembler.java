package assembler;

public class Assembler
{
	private String[][] programMemory;
	private String[][] errorLog;
	
	public static void main(String [] args)
	{
		String path = "C:\\Users\\Skyler Malinowski\\Documents\\GitHub\\RISCV-IDE-SIM\\src\\assembler";
		String filename = "test.txt";
		Parser myInst = new Parser();
		myInst.LoadFile(path,filename);
	}
}
