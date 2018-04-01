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

import riscv.RISCV;
import simulator.Simulator;
import riscv.Program;
import assembler.Assembler;
import texteditor.*;
import controller.SplashController.SplashScreen;
import controller.TextController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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

//import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controls main application screen
 * @author Alejandro Aguilar
 * @author Skyler Malinowski
 * @version April 2018
 */
public class MainController extends Application
{
	private Desktop desktop = Desktop.getDesktop();
	private String base = "RV32I";
	private ArrayList<String> exts;
	private RISCV riscv = new RISCV(this.base);
	private Simulator simulator;
	private Program program;
	
	@FXML
	private Button clear_button; // ID of button
	
	@FXML
	private TextArea console_text; // ID of console text area
	
	@FXML
	private TextArea textArea; // ID of console text area

	@FXML
	private MenuItem glossary_id;
	
	@FXML
	private MenuItem quit_id;
	
	@FXML
	private MenuItem newFile;
	
	@FXML
	private MenuItem onlineDoc_id;
	
	@FXML
	private MenuItem OpenButton;
	
	@FXML
	private MenuItem ModuleExtButton;
	
	@FXML
	private MenuItem SaveFile;
	
	@FXML
	private VBox editorPane;
	
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
	private void handleGlossary() throws Exception
	{
		Parent root = null;
		
		try
		{
			root = FXMLLoader.load(getClass().getResource("../application/Glossary.fxml"));
		}
		catch (IOException ex)
		{
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);	
		}
				
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void handleOnlineDoc() throws Exception
	{
		Parent root = null;
		
		try
		{
			root = FXMLLoader.load(getClass().getResource("../application/OnlineDoc.fxml"));
		}
		catch (IOException ex)
		{
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
		if(textArea.getText() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("There is some text in editor");
			alert.setContentText("Would you like to save?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    // ... user chose OK
				handleSaveFile();
				textArea.clear();
			} else {
			    // ... user chose CANCEL or closed the dialog
				
			}
		
			
		}

	}
	
	@FXML
    public void handleOpenFile()
	{
//    	Stage stage = new Stage();
////    	final FileChooser fileChooser = new FileChooser();
////
////    	OpenButton.setOnAction(
////                new EventHandler<ActionEvent>() {
////                    public void handle(ActionEvent e) {
////                        File file = fileChooser.showOpenDialog(stage);
////                        if (file != null) {
////                            openFile(file);
////                        }
////                    }
////                });
		
		//OpenButton.setOnAction((ActionEvent event)-> {
			Stage stage = new Stage();
	      	FileChooser fileChooser = new FileChooser();
                        File file = fileChooser.showOpenDialog(stage);
                        this.program = new Program(file);
                        
                        if (file != null) {
                            textArea.setText(readFile(file));
                        }
              //  });
    }
	
	
	
	@FXML
    public void handleSaveFile(){
//    	Stage stage = new Stage();
//		SaveFile.setOnAction(new EventHandler<ActionEvent>() {
//	        public void handle(ActionEvent e){
//	            FileChooser fileChooser = new FileChooser();
//	            fileChooser.setTitle("Save File");
//	            File file = fileChooser.showSaveDialog(stage);
//	            if (file != null) {
//	            		//txt.setTextArea(textArea);
//	            		SaveFile(textArea.getText(), file);
//	            }
//	        }
//	    });
    	
   		   	Stage stage = new Stage();
    				 FileChooser fileChooser = new FileChooser();
    				    //Show save file dialog
    				    File file = fileChooser.showSaveDialog(stage);
    				    this.program = new Program(file);
    				    
    				    if(file != null){
    				        SaveFile(textArea.getText(), file);
    				    }   		       
	}
	

	
	//class TextEditor extends Thread{	
	public void start(Stage primaryStage){
		
	//	@Override
	//	public void run()
	//	{
	//		try
	//		{
	//			Thread.sleep(1); // let it render 
	//			
	//			Platform.runLater(new Runnable() {
	//				@Override
	//				public void run() {
	//					// Loading Main GUI
	//					Parent root2 = null;
	//					try {
	//						//UI ui = new UI();
	//						//ui.setVisible(true);
	//						VBox pane = FXMLLoader.load(getClass().getResource("../application/TextEditor.fxml"));
	//						editorPane.getChildren().setAll(pane);
	//
	//					}catch (IOException ex){
	//						Logger.getLogger(TextController.class.getName()).log(Level.SEVERE, null, ex);	
	//					}
	//				
	//
	//				}
	//			});
	//		}
	//		catch (InterruptedException ex)
	//		{
	//			Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);	
	//		}
	//		
			
			
	//		SaveFile.setOnAction((ActionEvent event) -> {
	//	    	Stage stage = new Stage();
	//////	        FXMLLoader loader = new FXMLLoader(getClass().getResource("TextController.fxml"));
	//////			;
	//////			TextController controller = new TextController();
	//////			//controller.setTextArea(controller.getTextArea());
	//////			//TextArea txt = controller.getTextArea();
	//////			
	//////			VBox pane = new VBox();
	//////			pane.getChildren().add(controller);
	//			 FileChooser fileChooser = new FileChooser();
	////			    //Show save file dialog
	//			    File file = fileChooser.showSaveDialog(primaryStage);
	//			    if(file != null){
	//			        SaveFile(textArea.getText(), file);
	//			    }
	////			
	////
	////	       
	//	    });
	//		
	//		primaryStage.show();
		
	 
			
		 	
		 	
		//}
	}
	
	
	private void openFile(File file) {
	    try {
	        desktop.open(file);
	    } catch (IOException ex) {
	        Logger.getLogger(
	            MainController.class.getName()).log(
	                Level.SEVERE, null, ex
	            );
	    }
	}
	
	
	
	private void SaveFile(String content, File file){
		try {
	            FileWriter fileWriter = null; 
	            fileWriter = new FileWriter(file);
	            fileWriter.write(content);
	            fileWriter.close();
	        } catch (IOException ex) {
	            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	         
	    }
	
	private String readFile(File file){
	    StringBuilder stringBuffer = new StringBuilder();
	    BufferedReader bufferedReader = null;
	     
	    try {
	
	        bufferedReader = new BufferedReader(new FileReader(file));
	         
	        String text;
	        while ((text = bufferedReader.readLine()) != null) {
	            stringBuffer.append(text + "\n" );
	        }
	
	    } catch (FileNotFoundException ex) {
	        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
	        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
	        try {
	            bufferedReader.close();
	        } catch (IOException ex) {
	            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    } 
	     
	    return stringBuffer.toString();
	}
	
	/**
	 * Setter method for 'this.base'
	 * @param base
	 */
	@FXML
	private void setBase(String base)
	{
		this.base = base;
	}
	
	/**
	 * Setter method for RISCV class
	 * @param base
	 * @param ext
	 */
	@FXML
	private void setHardware()
	{
		this.riscv = new RISCV(this.base,this.exts);
	}
	
	/**
	 * Passes current hardware settings and active program to the assembler
	 */
	@FXML
	private void assembleProgram()
	{
		Assembler assembler = new Assembler(this.riscv);
		
		if (this.program.getFile() == null)
		{
			// TODO :: Error message "No file specified to be assembled"
		}
		else
		{
			assembler.assemble(this.program);
			
			if (this.program.getWarningList().size() > 0)
			{
				System.out.println("Assembler: completed with these warnings:\n");
				this.program.printWarningList();
			}
			else
			{
				System.out.println("Assembler: completed without warnings.\n");
			}

			if (this.program.getErrorList().size() > 0)
			{
				System.out.println("Assembler: completed with these errors:\n");
				this.program.setAssembled(false);
				this.program.printErrorList();
			}
			else
			{
				System.out.println("Assembler: completed without errors.\n");
				this.program.setAssembled(true);
			}
		}
		
		if (this.program.getAssebled() == true)
		{
			this.simulator = new Simulator(this.riscv, this.program);
		}
	}

	/**
	 * Simulates the next instruction
	 */
	@FXML
	private void simulatorForwardStep()
	{
		if (program.getAssebled() == true)
		{
			this.simulator.step();
		}
		else
		{
			// TODO :: Error message "Cannot simulate program before it is assembled"
		}
	}
	
	/**
	 * Simulates the program in its entirety
	 */
	@FXML
	private void simulatorRun()
	{
		if (program.getAssebled() == true)
		{
			this.simulator.run();
		}
		else
		{
			// TODO :: Error message "Cannot simulate program before it is assembled"
		}
	}
	
	/**
	 * Simulates the previous instruction
	 */
	@FXML
	private void simulatorBackStep()
	{
		if (program.getAssebled() == true)
		{
			this.simulator.backstep();
		}
		else
		{
			// TODO :: Error message "Cannot simulate program before it is assembled"
		}
	}
	
	/**
	 * Resets simulator to its initial state
	 */
	@FXML
	private void simulatorReset()
	{
		if (program.getAssebled() == true)
		{
			this.simulator.reset();
		}
		else
		{
			// TODO :: Error message "Cannot simulate program before it is assembled"
		}
	}
	
	
	/*  Is this even used? If not, delete this.
	public static void main(String[] args)
	{
		launch(args);
	}
	*/
	
}
