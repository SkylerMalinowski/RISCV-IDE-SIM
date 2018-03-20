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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import texteditor.*;

/**
 * Controls splash screen
 * @author Alejandro Agular
 * @author Skyler Malinowski
 * @version February 2018
 */
public class SplashController implements Initializable{

	@FXML
	private StackPane rootPane; // ID of panel
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		// Generating new Slash Screen thread
		new SplashScreen().start();
		
	}

	
	// Adding Thread to start loading MainGUI
	class SplashScreen extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				Thread.sleep(5000); // let it render 
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// Loading Main GUI
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("../application/MainGUI.fxml"));
					}
					catch (IOException ex){
						Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
					}
					
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
					//new UI().setVisible(true);
					rootPane.getScene().getWindow().hide(); // remove splash
					}
				});
			}
			catch (InterruptedException ex)
			{
				Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
			}
		}
	}
}
