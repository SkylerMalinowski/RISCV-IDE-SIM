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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controls main application screen
 * @author Skyler Malinowski
 * @version February 2018
 */


/* Allows to clear text on console */
public class MainController extends Application {
	@FXML
	private Button clear_button; // ID of button
	
	@FXML
	private TextArea console_text; // ID of console text area

	@FXML
	private MenuItem glossary_id;
	
	@FXML
	private MenuItem quit_id;
	
	@FXML
	private MenuItem onlineDoc_id;
	
	@FXML
	private void handleButtonAction(ActionEvent e) {
		console_text.clear();
	}
	
	@FXML
	private void handleQuitAction() {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	private void handleGlossary() throws Exception {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../application/Glossary.fxml"));
		}catch (IOException ex){
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);	
		}
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
	}

	@FXML
	private void handleOnlineDoc() throws Exception {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../application/OnlineDoc.fxml"));
		}catch (IOException ex){
			Logger.getLogger(OnlineDocController.class.getName()).log(Level.SEVERE, null, ex);	
		}
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
	}

@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

//	  clear_button.setOnAction(new EventHandler<ActionEvent>() {
//	        @Override public void handle(ActionEvent e) {
//	            console_text.clear();
//	        }
//	    });

	    //rimaryStage.show();
	
	
		// Start Splash screen first, it will load Main GUI
//	Parent root = null;
//	try {
//		root = FXMLLoader.load(getClass().getResource("../application/MainGUI.fxml"));
//	}catch (IOException ex){
//		Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
//	}
//			
//			Scene scene = new Scene(root);
//			Stage stage = new Stage();
//			stage.setScene(scene);
//			stage.show();
		
		

}


public static void main(String[] args) {
    launch(args);
    
	}
	
}
