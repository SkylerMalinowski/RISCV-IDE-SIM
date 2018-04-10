package controller;

public class Memory {
	
	
	private String word;
	private int Address;
	

	public String LoadMemory(int RegisterNumber)
	{
		return word;
	}
	
	public void StoreMemory(int RegisterNumber, String Data)
	{
		word = Data;
	}
}
