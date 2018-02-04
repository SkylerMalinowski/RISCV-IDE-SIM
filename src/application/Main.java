package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Start Splash screen first, it will load Main GUI
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		
			
		}	catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}