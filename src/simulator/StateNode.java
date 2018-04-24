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

package simulator;

public class StateNode
{
	private LocationType locationType;
	private String location;
	private String value;
	
	/**
	 * Constructor
	 * @param locationType
	 * @param location
	 * @param value
	 */
	public StateNode(LocationType locationType, String location, String value)
	{
		this.locationType = locationType;
		this.location = location;
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return String.format("<%s , %s :: %s>", this.locationType, this.location, this.value);
	}
	
	/**
	 * Getter method for 'this.locationType'
	 * @return
	 */
	public LocationType getType()
	{
		return this.locationType;
	}
	
	/**
	 * Getter method for 'this.location'
	 * @return
	 */
	public String getLocation()
	{
		return this.location;
	}
	
	/**
	 * Getter method for 'this.value'
	 * @return
	 */
	public String getValue()
	{
		return this.value;
	}
}
