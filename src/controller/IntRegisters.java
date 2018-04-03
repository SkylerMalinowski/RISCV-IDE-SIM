package controller;

public class IntRegisters {

	public String name;
	public int num;
	public int value;
	

	
	public IntRegisters(String names, int number, int values) {
		this.name = names;
		this.value = values;
		this.num = number;
	}

	public String getName()					// return the name of the register
	{
		return name;
	}
	
	public int getNum()					// return the value of the register
	{
		return num;
	}
	
	public int getValue() {					// get the value of register
		return value;
	}
	
	public void setValue(int val) {			// set the value of the register
		value = val;
	}


}
