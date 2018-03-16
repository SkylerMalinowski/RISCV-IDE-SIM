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


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Element;

import controller.SplashController.SplashScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import texteditor.*;

/**
 * Controls main application screen
 * @author Skyler Malinowski
 * @version February 2018
 */


/* Allows to clear text on console */
public class MainController implements Initializable{
//public class MainController extends Application {
	@FXML
	private Button clear_button; // ID of button
	
	@FXML
	private TextArea console_text; // ID of console text area

	@FXML
	private MenuItem glossary_id;
	
	@FXML
	private MenuItem quit_id;
	
	@FXML
	private MenuItem newFile;
	
	@FXML
	private MenuItem onlineDoc_id;
	
	@FXML
	private VBox editorPane;
	
	@FXML
	private MenuItem ModuleExtButton;
	

	
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
	
	@FXML
	private void handleModuleExtension() throws Exception {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../application/ModuleExtension.fxml"));
		}catch (IOException ex){
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);	
		}
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
	}
	

	@FXML
	private void handleNewFile() throws Exception {
		new TextEditor().start();
	}


	
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		// Generating new Slash Screen thread
		new TextEditor().start();
		
	}
//@Override
	
class TextEditor extends Thread{	
	
///public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub


	@Override
	public void run()
	{
		try
		{
			Thread.sleep(1); // let it render 
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// Loading Main GUI
					Parent root2 = null;
					try {
						UI ui = new UI();
						//ui.setVisible(true);
						VBox pane = FXMLLoader.load(getClass().getResource("../application/TextEditor.fxml"));
						editorPane.getChildren().setAll(pane);

					}catch (IOException ex){
						Logger.getLogger(OnlineDocController.class.getName()).log(Level.SEVERE, null, ex);	
					}
				

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
	


//public static void main(String[] args) {
//    launch(args);
//    
//	}
//	
//}
