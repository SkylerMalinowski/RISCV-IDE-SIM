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

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import texteditor.UI;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Launches application
 * @author Skyler Malinowski
 * @author Alejandro Aguilar
 * @version February 2018
 */
public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Start Splash screen first, it will load Main GUI
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		


	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}