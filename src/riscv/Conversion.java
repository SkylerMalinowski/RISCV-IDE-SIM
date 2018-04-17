package riscv;

public class Conversion {

	public String DecimalToBinary(String number)
	{
		int padding;
		String Binary="";
		String S="";
		String reverse;
		boolean negative=false, carry=false;
		if(number.charAt(0)=='-')
		{
			negative = true;
			number = number.substring(1);
		}
		int num = Integer.parseInt(number);
		do
		{
			if((num%2)==0)
			{
				Binary += "0";
			}
			else
			{
				Binary += "1";
			}
			
			num = num/2;
		}while(num!=0);
		
		padding = 32-Binary.length();	
		for(int i=0;i<padding;i++)
		{
			Binary = Binary + "0";	
		}
		if(negative==true)
		{
			System.out.println(reverse = new StringBuilder(Binary).reverse().toString());
			for(int j=0;j<32;j++)
			{
				if(Binary.charAt(j)=='0')		// invert the bits
				{
					S = S+ "1";
				}
				else if(Binary.charAt(j)=='1')	// invert the bits
				{
					S = S+ "0";
				}
			}
			System.out.println(reverse = new StringBuilder(S).reverse().toString());
			Binary="";
			carry = true;
			for(int i=0;i<32;i++)				
			{		
				if(S.charAt(i)=='1' && carry ==true)
				{
					Binary = Binary +"0";
					carry = true;			// there is still a carry on bit
				}
				else if(S.charAt(i)=='1'&& carry == false)
				{
					Binary = Binary +"1";
					carry = false;
				}
				else if(S.charAt(i)=='0'&&carry == true)
				{
					Binary = Binary + "1";
					carry = false;
				}
				else if(S.charAt(i)=='0'&& carry == false)
				{
					Binary = Binary+"0";	
				}
			}
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
	public String HextoDecimal(String number)
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
	/*
	public String StringtoBinary(String stream)
	{
		int IntegerASCII;
		String A, S="";
		for(int i=0;i<stream.length();i++)
		{
			IntegerASCII = stream.charAt(i);
			System.out.println(IntegerASCII);
			A = DecimaltoBinary(Integer.toString(IntegerASCII));
			System.out.println(A);
			S = S +" " +A;
		}
		String EightBitWord = new StringBuilder(S).reverse().toString();
		return EightBitWord;
	}
	*/
	public static void main(String[] args)
	{
		Conversion con = new Conversion();
		System.out.println(con.DecimalToBinary("-3"));
		
		//System.out.println(Integer.parseInt("1000",2));
	}
	
}
