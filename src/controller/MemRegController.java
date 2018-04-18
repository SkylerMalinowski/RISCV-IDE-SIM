package controller;

import controller.MainController;
import javafx.collections.ObservableList;

public class MemRegController
{
	public static void UpdateIntRegister(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock, 
			int index, int value)
	{
		String x = "x";
		for(int i=0; i < 31; i++)
		{
			if(index==intRegister.get(i).getNum())
			{
				x += index;
				intRegister.set(index, (new IntRegisters(x,index,value)));
				break;
			}
		}
	}
	
	
	public static void UpdateFloatRegister(ObservableList<IntRegisters> intRegister, ObservableList<FloatRegisters> floatRegister, ObservableList<Memory> memoryBlock, 
			int index, int value)
	{
		String f = "f";
		for(int i=0; i < 31; i++)
		{
			if(index==floatRegister.get(i).getNum())
			{
				f += index;
				floatRegister.set(index, (new FloatRegisters(f,index,value)));
				break;
			}
		}
	}
}
