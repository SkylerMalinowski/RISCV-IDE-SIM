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
	class SplashScreen extends Thread{
		@Override
		public void run() {
				try {
					Thread.sleep(5000); // let it render 
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// Loading Main GUI
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("../application/MainGUI.fxml"));
						}	catch (IOException ex){
							Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
						}
							Scene scene = new Scene(root);
							Stage stage = new Stage();
							stage.setScene(scene);
							stage.show();
							rootPane.getScene().getWindow().hide(); // remove splash
						}
							
				});

					
				}	catch (InterruptedException ex){
					Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
				}
		}
	}
}
