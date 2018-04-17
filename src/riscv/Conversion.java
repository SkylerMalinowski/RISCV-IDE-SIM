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
		int bit,Decimal=0;
		int length;
		length = number.length();		// get the maximum length
		while(length!=-1)
		{
			bit = number.charAt(length);		// Binary number start via 1001 for 4 bits, start with MSB
			if(bit==0)
			{
				Decimal = Decimal + 
			}
			length++;
		}
		return Decimal;
	}
	/*
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
	*/			//		"A12b"
	public String StringtoBinary(String stream)
	{
		int IntegerASCII;
		String A, S=null;
		for(int i=0;i<stream.length();i++)
		{
			//A12b
			IntegerASCII = stream.charAt(i);
			A = DecimaltoBinary(Integer.toString(IntegerASCII));
			S = S +" " +A;
		}
		String EightBitWord = new StringBuilder(S).reverse().toString();
		return EightBitWord;
	}
	
}
