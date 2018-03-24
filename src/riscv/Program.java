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

package riscv;

import assembler.Token;
import assembler.TokenType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to represent a file and its associations for processing
 * @author Skyler Malinowski
 * @version February 2018
 */
public class Program
{
	private File file;
	private ArrayList<String> source;
	private ArrayList<ArrayList<Token>> tokenList;
	private ArrayList<Token> tokenStream;
	private ArrayList<Token> labelList;
	private ArrayList<ArrayList<Token>> textList;
	private ArrayList<ArrayList<Token>> dataList;
	
	private ArrayList<ErrorMessage> errorList;
	private ArrayList<ErrorMessage> warningList;

	/**
	 * Constructor initializes all 'Program.class' variables, then sets file and source
	 * @param filePath
	 */
	public Program(String filePath)
	{
		this.source = new ArrayList<String>();
		this.tokenList = new ArrayList<ArrayList<Token>>();
		this.tokenStream = new ArrayList<Token>();
		this.labelList = new ArrayList<Token>();
		this.textList = new ArrayList<ArrayList<Token>>();
		this.dataList = new ArrayList<ArrayList<Token>>();
		this.errorList = new ArrayList<ErrorMessage>();
		this.warningList = new ArrayList<ErrorMessage>();
		
		setFile(filePath);
		setSource(getFile().getAbsolutePath());
	}
	
	/**
	 * Method gets 'this.filename'
	 * @return 'this.file'
	 */
	public File getFile()
	{
		return this.file;
	}
	
	/**
	 * Method sets 'this.file'
	 * @param filename
	 */
	public void setFile(String filePath)
	{
		this.file = new File(filePath);
	}
	
	/**
	 * Method gets 'this.source'
	 * @return 'this.source'
	 */
	public ArrayList<String> getSource()
	{
		return this.source;
	}
	
	/**
	 * Method sets 'this.source' with raw text from file
	 * @param filename
	 */
	public void setSource(String filePath)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String currentLine = null;

			while ((currentLine = br.readLine()) != null)
			{
				//System.out.println(currentLine);
				this.source.add(currentLine);
			}
		}
		catch(FileNotFoundException ex)
		{
			this.errorList.add(new ErrorMessage(ErrorMessage.ERROR,"Unable to open file '" + filePath + "'"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method gets 'this.tokenList'
	 * @return this.tokenList
	 */
	public ArrayList<ArrayList<Token>> getTokenList()
	{
		return this.tokenList;
	}
	
	/**
	 * Method sets 'this.tokenList'
	 * @param tokenList
	 */
	public void appendTokenList(ArrayList<Token> tokenList)
	{
		this.tokenList.add(tokenList);
	}
	
	/**
	 * Method gets 'this.tokenStream'
	 * @return this.tokenStream
	 */
	public ArrayList<Token> getTokenStream()
	{
		return this.tokenStream;
	}
	
	/**
	 * Generates tokenStream from stored lines of tokens
	 */
	public void buildTokenStream()
	{
		int line = 0;
		for (ArrayList<Token> tokenLine : this.getTokenList())
		{
			for (Token token : tokenLine)
			{
				this.tokenStream.add(token);
			}
			this.tokenStream.add(new Token(TokenType.EOL, null, line, 0, 0));
			++line;
		}
	}
	
	/**
	 * Method appends 'this.labelList'
	 */
	public void appendLabelList(Token token)
	{
		this.labelList.add(token);
	}
	
	/**
	 * Getter method for 'this.labelList'
	 * @return
	 */
	public ArrayList<Token> getLabelList()
	{
		return this.labelList;
	}
	
	/**
	 * Checks if a label has already been used
	 * @param token
	 * @return
	 */
	public boolean labelUsed(Token token)
	{
		for (Token t : this.labelList)
		{
			if (token.getData().toUpperCase().equals(t.getData().toUpperCase()))
				return true;
		}
		return false;
	}
	
	/**
	 * Method gets 'this.errorList'
	 * @return
	 */
	public ArrayList<ErrorMessage> getErrorList()
	{
		return this.errorList;
	}
	
	/**
	 * Method prints 'this.errorList'
	 */
	public void printErrorList()
	{
		for (ErrorMessage error : this.errorList)
		{
			System.out.println(error.toString());
		}
		System.out.println();
	}
	
	/**
	 * Method appends 'this.errorList'
	 * @param errorList
	 */
	public void appendErrorList(ErrorMessage error)
	{
		this.errorList.add(error);
	}
	
	/**
	 * Method gets 'this.errorList'
	 * @return
	 */
	public ArrayList<ErrorMessage> getWarningList()
	{
		return this.warningList;
	}
	
	/**
	 * Method prints 'this.warningList'
	 */
	public void printWarningList()
	{
		for (ErrorMessage warning : this.warningList)
		{
			System.out.println(warning.toString());
		}
		System.out.println();
	}
	
	/**
	 * Method appends 'this.warningList'
	 * @param errorList
	 */
	public void appendWarningList(ErrorMessage warning)
	{
		this.warningList.add(warning);
	}
	
	/**
	 * From tokens, generates only the text section
	 */
	public void buildText()
	{
		boolean found = false;
		
		for (ArrayList<Token> tokens : this.getTokenList())
		{
			try
			{
				if (tokens.get(0).getData().equalsIgnoreCase(".TEXT"))
				{
					found = true;
					this.appendTextList(tokens);
				}
				else if (tokens.get(0).getData().equalsIgnoreCase(".DATA"))
				{
					found = false;
				}
				
				if (found)
				{
					this.appendTextList(tokens);
				}
			} 
			catch (Exception ex)
			{
				// Do nothing
			}
		}
		
		if (this.getTextList().size() == 0)
		{
			this.textList = this.getTokenList();
		}
	}
	
	/**
	 * Getter method for 'this.textList'
	 * @return
	 */
	public ArrayList<ArrayList<Token>> getTextList()
	{
		return this.textList;
	}
	
	/**
	 * Method appends 'this.textList'
	 */
	public void appendTextList(ArrayList<Token> tokens)
	{
		this.textList.add(tokens);
	}
	
	/**
	 * From tokens, generates only the data section
	 */
	public void buildData()
	{
		boolean found = false;
		
		for (ArrayList<Token> tokens : this.getTokenList())
		{
			try
			{
				if (tokens.get(0).getData().equalsIgnoreCase(".DATA"))
				{
					found = true;
					this.appendTextList(tokens);
				}
				else if (tokens.get(0).getData().equalsIgnoreCase(".TEXT"))
				{
					found = false;
				}
				
				if (found)
				{
					this.appendDataList(tokens);
				}
			} 
			catch (Exception ex)
			{
				// Do nothing
			}
		}
	}
	
	/**
	 * Getter method for 'this.dataList'
	 * @return
	 */
	public ArrayList<ArrayList<Token>> getDataList()
	{
		return this.dataList;
	}
	
	/**
	 * Method appends 'this.textList'
	 */
	public void appendDataList(ArrayList<Token> tokens)
	{
		this.dataList.add(tokens);
	}
}
