/**
 * Copyright (C) 2018,  @authors
 * @author Skyler Malinowski  @email skyler.malinowski@gmail.com
 * @author Arjun Ohri         @email aohri@att.net
 * @author Alejandro Aguilar  @email alejandro.aguilar1195@gmail.com
 * @author Raj Balaji         @email nintedraj@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * GNU General Public License v3  
 * @link https://www.gnu.org/licenses/gpl.html
 */

package controller;

import controller.MainController;
import javafx.collections.ObservableList;

/**
 * Controller for Registers and Memory
 * @author Skyler Malinowski
 * @author Raj Balaji
 * @version April 2018
 */
public class MemRegController
{
	/**
	 * Getter method for IntRegister
	 * @param intRegister
	 * @param name
	 * @return
	 */
	public static int getIntRegister(ObservableList<IntRegisters> intRegister, String name)
	{
		name = SetRegName(name);
		int i;
		
		for (i = 0; i < intRegister.size(); ++i )
		{
			if (intRegister.get(i).getName().equalsIgnoreCase(name))
			{
				break;
			}
		}
		return intRegister.get(i).getValue();
	}
	
	/**
	 * Setter method for IntRegister
	 * @param intRegister
	 * @param name
	 * @param value
	 */
	public static void setIntRegister(ObservableList<IntRegisters> intRegister, String name, int value)
	{
		name = SetRegName(name);
		int i;
		for (i = 0; i < intRegister.size(); ++i )
		{
			if (intRegister.get(i).getName().equalsIgnoreCase(name))
			{
				intRegister.set(i, (new IntRegisters(name,i,value)));
				break;
			}
		}
	}
	
	/**
	 * Getter method for FloatRegister
	 * @param floatRegister
	 * @param name
	 * @return
	 */
	public static int getFloatRegister(ObservableList<FloatRegisters> floatRegister, String name)
	{
		name = SetRegName(name);
		int i;
		
		for (i = 0; i < floatRegister.size(); ++i )
		{
			if (floatRegister.get(i).getName().equalsIgnoreCase(name))
			{
				break;
			}
		}
		return floatRegister.get(i).getValue();
	}
	
	/**
	 * Setter method for FloatRegister
	 * @param floatRegister
	 * @param name
	 * @param value
	 */
	public static void setFloatRegister(ObservableList<FloatRegisters> floatRegister, String name, int value)
	{
		name = SetRegName(name);
		int i;
		for (i = 0; i < floatRegister.size(); ++i )
		{
			if (floatRegister.get(i).getName().equalsIgnoreCase(name))
			{
				floatRegister.set(i, (new FloatRegisters(name,i,value)));
				break;
			}
		}
	}
	
	/**
	 * Transforms register aliases into a standardized name
	 * @param name
	 * @return
	 */
	public static String SetRegName(String name)
	{
		switch (name)
		{
		// Universal
		case "pc" :
			name = "pc";
			break;
			
		// Integer Registers
		case "zero" :
			name = "x0";
			break;
		case "ra" :
			name = "x1";
			break;
		case "sp" :
			name = "x2";
			break;
		case "gp" :
			name = "x3";
			break;
		case "tp" :
			name = "x4";
			break;
		case "t0" :
			name = "x5";
			break;
		case "t1" :
			name = "x6";
			break;
		case "t2" :
			name = "x7";
			break;
		case "s0" :
		case "fp" :
			name = "x8";
			break;
		case "s1" :
			name = "x9";
			break;
		case "a0" :
			name = "x10";
			break;
		case "a1" :
			name = "x11";
			break;
		case "a2" :
			name = "x12";
			break;
		case "x13" :
			name = "x13";
			break;
		case "a4" :
			name = "x14";
			break;
		case "a5" :
			name = "x15";
			break;
		case "a6" :
			name = "x16";
			break;
		case "a7" :
			name = "x17";
			break;
		case "s2" :
			name = "x18";
			break;
		case "s3" :
			name = "x19";
			break;
		case "s4" :
			name = "x20";
			break;
		case "s5" :
			name = "x21";
			break;
		case "s6" :
			name = "x22";
			break;
		case "s7" :
			name = "x23";
			break;
		case "s8" :
			name = "x24";
			break;
		case "s9" :
			name = "x25";
			break;
		case "s10" :
			name = "x26";
			break;
		case "s11" :
			name = "x27";
			break;
		case "t3" :
			name = "x28";
			break;
		case "t4" :
			name = "x29";
			break;
		case "t5" :
			name = "x30";
			break;
		case "t6" :
			name = "x31";
			break;

		// Floating Registers
		case "ft0" :
			name = "f0";
			break;
		case "ft1" :
			name = "f1";
			break;
		case "ft2" :
			name = "f2";
			break;
		case "ft3" :
			name = "f3";
			break;
		case "ft4" :
			name = "f4";
			break;
		case "ft5" :
			name = "f5";
			break;
		case "ft6" :
			name = "f6";
			break;
		case "ft7" :
			name = "f7";
			break;
		case "fs0" :
			name = "f8";
			break;
		case "fs1" :
			name = "f9";
			break;
		case "fa0" :
			name = "f10";
			break;
		case "fa1" :
			name = "f11";
			break;
		case "fa2" :
			name = "f12";
			break;
		case "fa3" :
			name = "f13";
			break;
		case "fa4" :
			name = "f14";
			break;
		case "fa5" :
			name = "f15";
			break;
		case "fa6" :
			name = "f16";
			break;
		case "fa7" :
			name = "f17";
			break;
		case "fs2" :
			name = "f18";
			break;
		case "fs3" :
			name = "f19";
			break;
		case "fs4" :
			name = "f20";
			break;
		case "fs5" :
			name = "f21";
			break;
		case "fs6" :
			name = "f22";
			break;
		case "fs7" :
			name = "f23";
			break;
		case "fs8" :
			name = "f24";
			break;
		case "fs9" :
			name = "f25";
			break;
		case "fs10" :
			name = "f26";
			break;
		case "fs11" :
			name = "f27";
			break;
		case "ft8" :
			name = "f28";
			break;
		case "ft9" :
			name = "f29";
			break;
		case "ft10" :
			name = "f30";
			break;
		case "ft11" :
			name = "f31";
			break;
			
		default :
			break;
		}
		return name;
	}
	
	public static int getMemory(Memory memory, String name)
	{

		return 0;
	}
	
	
	public static void setMemory(Memory memory, String name, int value)
	{
		return;
	}
	
	/*
	 * Not needed anymore? Then delete this commented stuff
	 * But add memory getters and setters
	 
	public static void UpdateIntRegister(ObservableList<IntRegisters> intRegister, int index, int value)
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
	
	
	public static void UpdateFloatRegister(ObservableList<FloatRegisters> floatRegister, int index, int value)
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
	*/
}
