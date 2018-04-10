package riscv;

public class Conversion {

	public String DecimaltoBinary(String number)
	{
		String Binary=null;
		int num = Integer.parseInt(number);
		while(num!=0)
		{
			if(num%2==0)
			{
				Binary += "0";
			}
			else
			{
				Binary += "1";
			}
			num = num/2;
		}
		String binary = new StringBuilder(Binary).reverse().toString();
		return binary;
	}
	/*
	public String BinarytoDecimal(String number)
	{
		String Decimal;
		return Decimal;
	}
	
	public String DecimaltoHex(String number)
	{
		String Hex;
		return Hex;;
	}
	
	public String HextoDecimal(String number)
	{
		String Decimal;
		return Decimal;
	}
	
	public String DecimaltoOctal(String number)
	{
		String Octal;
		return Octal;
	}
	
	public String OctaltoDecimal(String number)
	{
		String Decimal;
		return Decimal;
	}
	
	public String HextoBinary(String number)
	{
		String Binary;
		return Binary;
	}
	
	public String BinarytoHex(String number)
	{
		String Hex;
		return Hex;
	}
	
	public String HextoOctal(String number)
	{
		String Octal;
		return Octal;
	}
	
	public String OctaltoHex(String number)
	{
		String Hex;
		return Hex;
	}
	
	public String OctaltoBinary(String number)
	{
		String Binary;
		return Binary;
	}
	
	public String BinarytoOctal(String number)
	{
		String Octal;
		return Octal;
	}
	*/
}
