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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Modality;
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
	private final static String [] arryayBase = {"RV32I", "RV64I"};
	private final static String [] arrayExtension = {"A", "C", "D", "F", "M", "N", "Q","V"};
	private static List<String> dialogBase;
	private static List<String> dialogExtension;
	private static ArrayList<String> Hardware_Extension;

	
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
					
					/* Check if stage is showing, prompt hardware questions */ 
					if(stage.isShowing() == true) {   	  
				     
				     dialogBase = Arrays.asList(arryayBase);
				     ChoiceDialog dialog = new ChoiceDialog(dialogBase.get(0), dialogBase);
				     
				     
				     dialog.setHeaderText("Select your hardware choice");
				     dialog.initModality(Modality.APPLICATION_MODAL);
				     Optional<String> result = dialog.showAndWait();

				     String selected = "cancelled.";
			    	 	 Hardware_Extension = new ArrayList<String>();

				     /* check result of hardware */
				     if (result.isPresent()) {
				    	 	selected = result.get();
				    	 	Hardware_Extension.add(selected);
				    	 	if(selected == "RV32I") {
				    	 		dialogExtension = Arrays.asList(arrayExtension);
							ChoiceDialog dialog_extension = new ChoiceDialog(dialogExtension.get(0), dialogExtension);
							dialog_extension.setHeaderText("Select your Extension choice");
							dialog_extension.initModality(Modality.APPLICATION_MODAL);
							Optional<String> resultExtension = dialog_extension.showAndWait();

						     String selectedExtension = "cancelled.";
						     if (resultExtension.isPresent()) {
						    	 	selectedExtension = resultExtension.get();
						    	 	Hardware_Extension.add(selectedExtension);
				    	 			}
					         System.out.println(selectedExtension);

				    	 	}
				    	 	if(selected.equals("RV64I")) {
				    			dialogExtension = Arrays.asList(arrayExtension);
								ChoiceDialog dialog_extension = new ChoiceDialog(dialogExtension.get(0), dialogExtension);
								dialog_extension.setHeaderText("Select your Extension choice");
								dialog_extension.initModality(Modality.APPLICATION_MODAL);
								Optional<String> resultExtension = dialog_extension.showAndWait();

							     String selectedExtension = "cancelled.";
							     if (resultExtension.isPresent()) {
							    	 	selectedExtension = resultExtension.get();
							    	 	System.out.println("it worked");
							    	 	Hardware_Extension.add(selectedExtension);
					    	 			}
						         System.out.println(selectedExtension);

				    	 	}
				    	 	if(selected.equals("")) {
				    	 		dialog.close();
				    	 		// assume 32 base
				    	 	}
				         System.out.println(selected);
				         System.out.println("Arraylist contains " + Hardware_Extension.get(0) + "," + Hardware_Extension.get(1));
				    }
				     
				     
					
				 }}
				});
			}	
			catch (InterruptedException ex)
			{
				Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
			}
		}
	}
	
	
	public String get_values(ArrayList<String> values) {
		String setting = null;
		for(int i = 0; i < values.size(); i++) {
		setting = values.get(i) + "";

		}
		return setting;
	}
}
