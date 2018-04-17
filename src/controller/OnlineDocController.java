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

/**
 * Controls online documentation
 * 
 * @author Alejandro Aguilar
 * @version February 2018
 */



public class OnlineDocController extends Application 
{
	
	@FXML
	private WebView browser;
	
	@FXML
	private ScrollPane scrollPane;
	
	private  WebEngine webEngine;

@Override public void start(Stage stage) 
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
    	webEngine.load("https://en.wikipedia.org/wiki/RISC-V");

    	scene.setRoot(scrollPane);
    	stage.setScene(scene);
    	stage.show();
  }
    public static void main(String[] args)
    {
        launch(args);
    }
}


