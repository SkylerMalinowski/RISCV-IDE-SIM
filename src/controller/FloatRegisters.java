package controller;

public class FloatRegisters {
	
	
	private String name;
	private int num;
	private int value;
	
	public FloatRegisters(String name, int number, int values) {
		this.name = new String(name);
		this.num = number;
		this.value = values;
		
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
