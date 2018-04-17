package controller;

import java.util.ArrayList;

public class Memory {
	
	
	public String word;
	private int Address;
	
	public Memory(String word, int Address)		// Constructor to Initialize the memory 
	{											// represented as words, each value is shown as null initially
		this.word=null;
		this.Address=Address;
	}

	public String LoadWord(int SourceAddress)
	{
		if(SourceAddress==Address)
		{
		return word;
		}
		else
		return null;
	}
	public void StoreMemory(int SourceAddress, String Data)
	{
		if(SourceAddress==Address)
		{
		word = Data;
		}
	}
	
}
