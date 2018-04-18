package controller;

import java.util.ArrayList;

public class Memory {
	
	
	public String value;
	public int address;
	
	public Memory(String Value, int Address)		// Constructor to Initialize the memory 
	{											// represented as words, each value is shown as null initially
		this.value=Value;
		this.address=Address;
	}

	public String getvalue(int SourceAddress)
	{
		return value;
	}
	public void setvalue(int SourceAddress, String Data)
	{
		if(SourceAddress==address)
		{
		value = Data;
		}
	}
	public void setaddress(int SourceAddress)
	{
		address = SourceAddress;
	}
	
}
