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

import riscv.*;
import simulator.*;
import assembler.*;
import texteditor.*;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controls main application screen
 * @author Alejandro Aguilar
 * @author Skyler Malinowski
 * @version April 2018
 */
public class MainController extends Application implements Initializable
{
	@FXML
	private Button clear_button; // ID of button
	
	@FXML
	private TextArea console_text; // ID of console text area
	
	@FXML
	private TextArea textArea; // ID of editor
	
	@FXML
	private TextArea LineNumberBar; 
	
	@FXML
	private TextField CursorStatusBar; // ID of editor status bar

	@FXML
	private MenuItem glossary_id;	// ID of pdf page
	
	@FXML
	private MenuItem quit_id;	// ID of quit item
	
	@FXML
	private MenuItem newFile;	// ID of new file item
	
	@FXML
	private MenuItem onlineDoc_id; // ID of wiki page
	
	@FXML
	private MenuItem OpenButton;	// ID of open file button
	
	@FXML
	private MenuItem hardware;	// ID of hardware settings page
	
	@FXML
	private MenuItem SaveFile;	// ID of Save file button
	
	@FXML
	private MenuItem newEditor;	// ID of new Editor button
	
	@FXML
	private Menu Recent;		// ID of open recent
	
	@FXML
	private MenuBar menuBar;		// ID of menuBar
	
	@FXML
	private VBox editorPane;		// ID contains textarea and console		

	@FXML
	private VBox Main;	// ID of main Vbox
	
	@FXML
	private ListView parsedText;
	
	@FXML
	private TabPane tabPane;	// ID of tab Pane
	
	@FXML
	private Tab defaultEditor; // ID of default tab
	
	@FXML
	private Tab executeTab;	// ID of execute tab
	
	@FXML private TableView<IntRegisters> Table1;
				
	@FXML private TableColumn<IntRegisters, String> IntNameColumn = new TableColumn<>("name");
		
	@FXML private TableColumn<IntRegisters, String> IntNumColumn = new TableColumn<>("num");
		
	@FXML private TableColumn<IntRegisters, String> IntValueColumn = new TableColumn<>("value");

	
	@FXML private TableView<FloatRegisters> Table2;

	@FXML private TableColumn<FloatRegisters, String> FloatNameColumn = new TableColumn<>("FloatName");
		
	@FXML private TableColumn<FloatRegisters, String> FloatNumColumn = new TableColumn<>("FloatNumber");
		
	@FXML private TableColumn<FloatRegisters, String> FloatValueColumn = new TableColumn<>("FloatValues");
	
	
	@FXML private TableView<Memory> Table3;
	
	@FXML private TableColumn<Memory, String> MemoryValueColumn0 = new TableColumn<>("value0");
	@FXML private TableColumn<Memory, String> MemoryValueColumn1 = new TableColumn<>("value1");
	@FXML private TableColumn<Memory, String> MemoryValueColumn2 = new TableColumn<>("value2");
	@FXML private TableColumn<Memory, String> MemoryValueColumn3 = new TableColumn<>("value3");
	
	@FXML private TableColumn<Memory, String> MemoryAddressColumn = new TableColumn<>("address");
	
	private ObservableList<IntRegisters> IntRegister = FXCollections.observableArrayList();
	private ObservableList<FloatRegisters> FloatRegister= FXCollections.observableArrayList();
	public ObservableList<Memory> MemoryBlock = FXCollections.observableArrayList();
	//private ObservableList<String> parsedText = FXCollections.observableArrayList();

	
    private Desktop desktop = Desktop.getDesktop();
	private String base = "RV32I";
	private ArrayList<String> exts;
	private RISCV riscv = new RISCV(this.base);
	private Simulator simulator;
	private Program program;
	private int count = 0;
	private File globalFile;
	private TextArea defaultTextArea =  new TextArea();
	private TextArea previousTextArea =  new TextArea();

	
	public void setText(String text) 
	{
		this.console_text.setText(System.out.toString());
	}
	
	public void getText() 
	{
		this.console_text.getText();
	}
	
	/**
	 *  Clears the Console
	 */
	
	@FXML
	private void handleButtonAction(ActionEvent e) 
	{
		console_text.clear();
	}
	
	/**
	 * Closes the entire program
	 */
	
	@FXML
	private void handleQuitAction() 
	{
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
		DocController online = new DocController();
		online.start(stage);
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
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);	
		}
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		OnlineDocController online = new OnlineDocController();
		online.start(stage);
		stage.show();
	}
	
	@FXML
	private void handleModuleExtension() throws Exception 
	{
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
	private void handleNewFile() throws Exception 
	{
		count = 0;
		if(defaultTextArea.getText().length() == 0) {
			defaultTextArea.clear();
		}else if(defaultTextArea.getText() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setHeaderText("There is some text in editor");
			alert.setContentText("Would you like to save?");
			
			ButtonType buttonTypeone = new ButtonType("Yes");
			ButtonType buttonTypetwo = new ButtonType("No");
			alert.getButtonTypes().setAll(buttonTypeone,buttonTypetwo);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeone){
			    // ... user chose OK
				handleSaveFile();
				defaultTextArea.clear();
			} else if(result.get() == buttonTypetwo){
				defaultTextArea.clear();
			}
		
			
		} 

	}
	
	@FXML
    public void handleOpenFile()
	{
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		defaultEditor = tabPane.getSelectionModel().getSelectedItem();
		if(tabPane.getSelectionModel().getSelectedIndex() == 0)
		{
			defaultTextArea = textArea;
				}else if(tabPane.getSelectionModel().getSelectedIndex() > 0) {
					defaultTextArea = previousTextArea;
				}
		File file = fileChooser.showOpenDialog(stage);
		globalFile = file;
		this.program = new Program(file);           
		if (file != null) 
		{
			defaultTextArea.setText(readFile(file));
		}
		count++;
            
    }
	
	
	
	@FXML
    public void handleSaveFile()
	{    
		defaultEditor = tabPane.getSelectionModel().getSelectedItem();
		if(tabPane.getSelectionModel().getSelectedIndex() == 0)
		{
			defaultTextArea = textArea;
		}else if(tabPane.getSelectionModel().getSelectedIndex() > 0) {
			defaultTextArea = previousTextArea;
		}
		if(count == 0)
		{
		Stage stage = new Stage();
    		FileChooser fileChooser = new FileChooser();
    		//Show save file dialog
    		File file = fileChooser.showSaveDialog(stage);
    		globalFile = file;
    		this.program = new Program(file);
    			    
    		if(file != null){
    			SaveFile(defaultTextArea.getText(), file);
    			}
    		count++;
		}else {
			count++;
			SaveFile(defaultTextArea.getText(), globalFile);
		}
    		
	}
	
	@FXML
    public void handleSaveAsFile()
	{    	
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		defaultEditor = tabPane.getSelectionModel().getSelectedItem();
		if(tabPane.getSelectionModel().getSelectedIndex() == 0)
		{
			defaultTextArea = textArea;
		}else if(tabPane.getSelectionModel().getSelectedIndex() > 0) {
			defaultTextArea = previousTextArea;
		}
		//Show save file dialog
		File file = fileChooser.showSaveDialog(stage);
		this.program = new Program(file);
		
		if(file != null)
		{
			SaveFile(defaultTextArea.getText(), file);
		}   		       
	}
	
	@FXML
	private void handleRecent()
	{
		//menuBar.getMenus().add(Recent);
		//ObservableList<String> FileNames = FXCollections.observableArrayList();
		//FileNames.add(globalFile.getName());
		MenuItem newItemFile = new MenuItem(globalFile.getName());
		Recent.getItems().add(newItemFile);
		newItemFile.setOnAction((ActionEvent event)-> {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		globalFile = file;
		this.program = new Program(file);           
		if (file != null)
			{
			 	defaultTextArea.setText(readFile(file));
			}
		count++;
            
		});
		
	}
	
	/**
	 * Simulates the next instruction
	 * adding multiple tabs feature, unfortunately only
	 * supports two tabs open at the moment 
	*/
	
	@FXML
	private void handleNewEditor()
	{
		Tab newTab = new Tab();
		tabPane.getTabs().add(newTab);
	    newTab.setText("New Editor");
	    TextArea newTextArea = new TextArea();
		newTab.setContent(newTextArea);
		defaultEditor = newTab;
		previousTextArea = newTextArea;
		defaultTextArea = newTextArea;
		newEditor.setDisable(true);
		
	}
	
	@FXML
	private void handleCloseTab()
	{
		tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
		newEditor.setDisable(false);
	}
	
	
	
	public void InitializeParsedText()
	{
		ArrayList<String> instruct = new ArrayList<String>();
		String line = "";
		for (ArrayList<Token> tokenArray : program.getTextList())
		{
			for (Token token : tokenArray)
			{
				line += token.getData() + " ";
			}
			instruct.add(line);
			line = "";
		}
		
		parsedText.setItems(FXCollections.observableArrayList(instruct));
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(executeTab);
	}
	
	public ObservableList<IntRegisters> InitializeIntRegisters()
	{
		// get 32 or 64 option  
		IntRegister.add(new IntRegisters("x0",0,0));
		IntRegister.add(new IntRegisters("x1",1,0));
		IntRegister.add(new IntRegisters("x2",2,0));
		IntRegister.add(new IntRegisters("x3",3,0));
		IntRegister.add(new IntRegisters("x4",4,0));
		IntRegister.add(new IntRegisters("x5",5,0));
		IntRegister.add(new IntRegisters("x6",6,0));
		IntRegister.add(new IntRegisters("x7",7,0));
		IntRegister.add(new IntRegisters("x8",8,0));
		IntRegister.add(new IntRegisters("x9",9,0));
		IntRegister.add(new IntRegisters("x10",10,0));
		IntRegister.add(new IntRegisters("x11",11,0));
		IntRegister.add(new IntRegisters("x12",12,0));
		IntRegister.add(new IntRegisters("x13",13,0));
		IntRegister.add(new IntRegisters("x14",14,0));
		IntRegister.add(new IntRegisters("x15",15,0));
		IntRegister.add(new IntRegisters("x16",16,0));
		IntRegister.add(new IntRegisters("x17",17,0));
		IntRegister.add(new IntRegisters("x18",18,0));
		IntRegister.add(new IntRegisters("x19",19,0));
		IntRegister.add(new IntRegisters("x20",20,0));
		IntRegister.add(new IntRegisters("x21",21,0));
		IntRegister.add(new IntRegisters("x22",22,0));
		IntRegister.add(new IntRegisters("x23",23,0));
		IntRegister.add(new IntRegisters("x24",24,0));
		IntRegister.add(new IntRegisters("x25",25,0));
		IntRegister.add(new IntRegisters("x26",26,0));
		IntRegister.add(new IntRegisters("x27",27,0));
		IntRegister.add(new IntRegisters("x28",28,0));
		IntRegister.add(new IntRegisters("x29",29,0));
		IntRegister.add(new IntRegisters("x30",30,0));
		IntRegister.add(new IntRegisters("x31",31,0));
		IntRegister.add(new IntRegisters("pc",0,0));
		
		return IntRegister;
	}
	
	
	public ObservableList<Memory> InitializeMemoryBlocks()
	{
		for(int i=0;i<50;i++)
		{
			MemoryBlock.add(new Memory("0","0","0","0",i*16));
		}
		return MemoryBlock;
	}
	
	public ObservableList<FloatRegisters> InitializeFloatRegisters()
	{
		
		FloatRegister.add(new FloatRegisters("f0",0,0));
		FloatRegister.add(new FloatRegisters("f1",0,0));
		FloatRegister.add(new FloatRegisters("f2",0,0));
		FloatRegister.add(new FloatRegisters("f3",0,0));
		FloatRegister.add(new FloatRegisters("f4",0,0));
		FloatRegister.add(new FloatRegisters("f5",0,0));
		FloatRegister.add(new FloatRegisters("f6",0,0));
		FloatRegister.add(new FloatRegisters("f7",0,0));
		FloatRegister.add(new FloatRegisters("f8",0,0));
		FloatRegister.add(new FloatRegisters("f9",0,0));
		FloatRegister.add(new FloatRegisters("f11",0,0));
		FloatRegister.add(new FloatRegisters("f12",0,0));
		FloatRegister.add(new FloatRegisters("f13",0,0));
		FloatRegister.add(new FloatRegisters("f14",0,0));
		FloatRegister.add(new FloatRegisters("f15",0,0));
		FloatRegister.add(new FloatRegisters("f16",0,0));
		FloatRegister.add(new FloatRegisters("f17",0,0));
		FloatRegister.add(new FloatRegisters("f18",0,0));
		FloatRegister.add(new FloatRegisters("f19",0,0));
		FloatRegister.add(new FloatRegisters("f20",0,0));
		FloatRegister.add(new FloatRegisters("f21",0,0));
		FloatRegister.add(new FloatRegisters("f22",0,0));
		FloatRegister.add(new FloatRegisters("f23",0,0));
		FloatRegister.add(new FloatRegisters("f24",0,0));
		FloatRegister.add(new FloatRegisters("f25",0,0));
		FloatRegister.add(new FloatRegisters("f26",0,0));
		FloatRegister.add(new FloatRegisters("f27",0,0));
		FloatRegister.add(new FloatRegisters("f28",0,0));
		FloatRegister.add(new FloatRegisters("f29",0,0));
		FloatRegister.add(new FloatRegisters("f30",0,0));
		FloatRegister.add(new FloatRegisters("f31",0,0));
		return FloatRegister;
	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		IntNameColumn.setCellValueFactory(new PropertyValueFactory<IntRegisters, String>("name"));
		IntNumColumn.setCellValueFactory(new PropertyValueFactory<>("num"));	
		IntValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		Table1.setItems(InitializeIntRegisters());
		
		FloatNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		FloatNumColumn.setCellValueFactory(new PropertyValueFactory<>("num"));	
		FloatValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		Table2.setItems(InitializeFloatRegisters());
		
		MemoryValueColumn0.setCellValueFactory(new PropertyValueFactory<>("value0"));
		MemoryValueColumn1.setCellValueFactory(new PropertyValueFactory<>("value1"));
		MemoryValueColumn2.setCellValueFactory(new PropertyValueFactory<>("value2"));
		MemoryValueColumn3.setCellValueFactory(new PropertyValueFactory<>("value3"));
		MemoryAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		Table3.setItems(InitializeMemoryBlocks());
		
		
	}


	@Override
	public void start(Stage primaryStage)
	{
		// TODO Auto-generated method stub
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
		if (this.program == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Error!");
			alert.setContentText("No file specified to be assembled.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				alert.close();
			}
		}
		else
		{
			if (this.program.getAssebled() == false)
				assembler.assemble(this.program);
			
			if (this.program.getWarningList().size() > 0)
			{
				System.out.println("Assembler: completed with these warnings:\n");
				for (String msg : this.program.printWarningList())
				{
					console_text.appendText(msg);
				}
				console_text.appendText("\n");
			}
			else
			{
				System.out.println("Assembler: completed without warnings.\n");
				console_text.appendText("Assembler: completed without warnings.\n");
			}
			if (this.program.getErrorList().size() > 0)
			{
				this.program.setAssembled(false);
				System.out.println("Assembler: completed with these errors:\n");
				console_text.appendText("Assembler: completed with these errors:\n");
				for (String msg: this.program.printErrorList())
				{
					console_text.appendText(msg);
				}
				console_text.appendText("\n");
			}
			else
			{
				this.program.setAssembled(true);
				System.out.println("Assembler: completed without errors.\n");
				console_text.appendText("Assembler: completed without errors.\n");
			}
		}
		
		if (this.program.getAssebled() == true)
		{
			this.simulator = new Simulator(this.riscv, this.program, this.MemoryBlock);
			InitializeParsedText();
		}
	}
	
	
	private void openFile(File file) 
	{
		try
		{
			desktop.open(file);
		} 
		catch (IOException ex) 
		{
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	private void SaveFile(String content, File file)
	{
		try
		{
			FileWriter fileWriter = null; 
	        fileWriter = new FileWriter(file);
	        fileWriter.write(content);
	        defaultEditor.setText(file.getName()); 
	        fileWriter.close();
	        
	        this.program.setAssembled(false);  
		}
		catch (IOException ex)
		{
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}

	
	private String readFile(File file)
	{
		StringBuilder stringBuffer = new StringBuilder();
	    BufferedReader bufferedReader = null; 
	    try {
	    		bufferedReader = new BufferedReader(new FileReader(file));
	         
	        String text;
	        defaultEditor.setText(file.getName()); 
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
	 * Simulates the next instruction
	 */
	@FXML
	private void simulatorForwardStep()
	{
		if(this.program == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Error!");
			alert.setContentText("Cannot simulate program before it is assembled.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				alert.close();
			}
		}
		
		if (program.getAssebled() == true)
		{
			this.simulator.step(IntRegister, FloatRegister, MemoryBlock);
		}
	}
	
	/**
	 * Simulates the program in its entirety
	 */
	@FXML
	private void simulatorRun()
	{
		if(this.program == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Error!");
			alert.setContentText("Cannot simulate program before it is assembled.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				alert.close();
			}
		}
		
		if (program.getAssebled() == true)
		{
			this.simulator.run(IntRegister, FloatRegister, MemoryBlock);
		}
	}
	
	/**
	 * Simulates the previous instruction
	 */
	@FXML
	private void simulatorBackStep()
	{
		if(this.program == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Error!");
			alert.setContentText("Cannot simulate program before it is assembled.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				alert.close();
			}
		}
		
		if (program.getAssebled() == true)
		{
			this.simulator.backStep(IntRegister, FloatRegister, MemoryBlock);
		}
	}
	
	/**
	 * Resets simulator to its initial state
	 */
	@FXML
	private void simulatorReset()
	{
		
		if(this.program == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Error!");
			alert.setContentText("Cannot simulate program before it is assembled.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				alert.close();
			}
		}
		
		if (program.getAssebled() == true)
		{
			this.simulator.reset(IntRegister, FloatRegister, MemoryBlock);
		}
	}
}
