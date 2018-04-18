package riscv;

public class Conversion {

	public static String ToBinary(String number, int width, Program program)
	{
		// Hexadecimal
		if (number.split("-")[1].startsWith("0x") || number.split("-")[1].startsWith("0X"))
		{
			return DecimalToBinary(""+Integer.parseInt(number.split("0[xX]")[1], 16), width, program);
		}
		// Octal
		else if (number.split("-")[1].startsWith("0o") || number.split("-")[1].startsWith("0O"))
		{
			return DecimalToBinary(""+Integer.parseInt(number.split("0[oO]")[1], 8), width, program);
		}
		// Binary
		else if (number.split("-")[1].startsWith("0b") || number.split("-")[1].startsWith("0B"))
		{
			if (number.charAt(0) == '-')
			{
				return DecimalToBinary("-"+Integer.parseInt(number.split("0[bB]")[1], 2), width, program);
			}
			else
			{
				return Integer.toBinaryString(Integer.parseInt(number.split("0[bB]")[1],2));
			}
		}
		// Decimal
		else
		{
			return DecimalToBinary(""+Integer.parseInt(number, 10), width, program);
		}
	}

	
	public static String DecimalToBinary(String number, int width, Program program)
	{
		int padding;
		String Binary = "";
		String S = "";
		boolean negative = false, carry = false;
		int num = Integer.parseInt(number);
		
		if(number.charAt(0) == '-')
		{
			negative = true;
			number = number.substring(1);
		}
		
		// Handles overflow
		if (num % (int) (Math.pow(2, width-1) - 1) != 0)
		{
			num = num % (int) (Math.pow(2, width-1) - 1);
			program.appendWarningList(new ErrorMessage(ErrorMessage.WARNING, "Number Overflow"));
		}
		
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
			
			num /= 2;
		} while( num != 0 && Binary.length() < width - 1 );
		
		padding = width-Binary.length();	
		for (int i=0; i < padding; i++)
		{
			Binary += "0";	
		}
		if (negative == true)
		{
			for (int j=0; j < width; j++)
			{
				if(Binary.charAt(j)=='0')		// invert the bits
				{
					S += "1";
				}
				else if(Binary.charAt(j)=='1')	// invert the bits
				{
					S += "0";
				}
			}
			
			Binary = "";
			carry = true;
			for (int i=0; i < width; i++)				
			{		
				if (S.charAt(i) == '1' && carry == true)
				{
					Binary += "0";
					carry = true;			// there is still a carry on bit
				}
				else if (S.charAt(i) == '1'&& carry == false)
				{
					Binary += "1";
					carry = false;
				}
				else if (S.charAt(i) == '0'&& carry == true)
				{
					Binary += "1";
					carry = false;
				}
				else if (S.charAt(i) == '0' && carry == false)
				{
					Binary += "0";	
				}
			}
		}
		
		return new StringBuilder(Binary).reverse().toString();
	}
}
