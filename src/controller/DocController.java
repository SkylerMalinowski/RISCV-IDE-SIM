package controller;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DocController extends Application
{
	@FXML
	private WebView browser;
	
	@FXML
	private ScrollPane scrollPane;
	
	private  WebEngine webEngine;
	
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = new Scene(new Group());
		browser = new WebView();
    		webEngine = browser.getEngine();
    		scrollPane = new ScrollPane();
    		scrollPane.setContent(browser);
    		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
    		@Override
    		public void changed(ObservableValue ov, State oldState, State newState) 
    		{
    			if (newState == Worker.State.SUCCEEDED) 
    			{
    				stage.setTitle(webEngine.getLocation());
    			}
    		}       
    	});
    		webEngine.load("https://riscv.org/specifications/");

    		scene.setRoot(scrollPane);
    		stage.setScene(scene);
    		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}