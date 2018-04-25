package controller;

import java.util.ArrayList;

public class Memory {
	
	public String value0;	
	public String value1;
	public String value2;
	public String value3;
	public int address;
	
	public Memory(String Value0, String Value1, String Value2, String Value3, int Address)		// Constructor to Initialize the memory 
	{											// represented as words, each value is shown as null initially
		
		this.value0 = Value0;
		this.value1 = Value1;
		this.value2 = Value2;
		this.value3 = Value3;
		this.address=Address;
	}
	public String getValue0()
	{
		return this.value0;
	}
	
	public String getValue1()
	{
		return this.value1;
	}
	
	public String getValue2()
	{
		return this.value2;
	}
	
	public String getValue3()
	{
		return this.value3;
	}
	public void setAddress(int SourceAddress)
	{
		address = SourceAddress;
	}
	public void setValue0(String Value0)
	{
			this.value0=Value0;// set the string value 
	}
	public void setValue1(String Value1)
	{
			this.value1=Value1; // set the string value 	
	}
	public void setValue2(String Value2)
	{
			this.value2=Value2;// set the string value 
	}
	public void setValue3(String Value3)
	{
				this.value3=Value3;
	}
	public int getAddress()
	{
		return this.address;
	}
	
}
