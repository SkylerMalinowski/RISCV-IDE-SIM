package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//
//import java.awt.Color;
//import java.io.File;
//import java.util.ArrayList;
//
//import javax.swing.JTextArea;
//
//import com.sun.javafx.css.StyleCacheEntry.Key;
//
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.InputEvent;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.VBox;
//import texteditor.AutoComplete;
//import texteditor.HighlightText;
//import texteditor.Keywords;
//
//
//
public class TextController extends Application {
	
	
	@FXML
	private TextArea textArea; // ID of console text area
//	
	@FXML
    public void handleOpenFile(){

//    	OpenButton.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    public void handle(final ActionEvent e) {
//                        File file = fileChooser.showOpenDialog(stage);
//                        if (file != null) {
//                            openFile(file);
//                        }
//                    }
//                });
    }
@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	
}
	
	 
	
//	
//	
//	@FXML
//	private TextArea textArea; // ID of console text area
//
//    
//	// Define an event handler
//	@SuppressWarnings("rawtypes")
//	EventHandler handler = new EventHandler<KeyEvent>() {
//	    public void handle(KeyEvent event) {
//	   Key 	key = lookupKey(event.getCode());
//            if (key != null) {
//                key.hashCode();
//
//                event.consume();
//            }
//	    }
//
//		private Key lookupKey(KeyCode code) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	};
//	
//
//	    private Keywords kw = new Keywords();
//	    private HighlightText languageHighlighter = new HighlightText(Color.WHITE);
//	    AutoComplete autocomplete;
//	    private boolean hasListener = false;
//	    private boolean edit = false;
//
//	 
//	 
//
//	        
//	    
//	
//	
//	 // Enable autocomplete option
//    public void enableAutoComplete(File file) {
//        if (hasListener) {
//            textArea.getParent().removeEventHandler(KeyEvent.KEY_PRESSED, handler);
//            hasListener = false;
//        }
//
//        
//        
//        
//        ArrayList<String> arrayList;
//        String[] list = kw.getSupportedLanguages();
//
//        for (int i = 0; i < list.length; i++) {
//            if (file.getName().endsWith(list[i])) {
//                switch (i) {
//                    case 0:
//                    	String[] sk = kw.getSKeywords();
//                    	arrayList = kw.setKeywords(sk);
//                        autocomplete = new AutoComplete(this, arrayList);
//                        textArea.getParent().addEventHandler(KeyEvent.KEY_PRESSED, handler);
//                        hasListener = true;
//                        break;
//                    case 1:
//                    	String[] asmk = kw.getasmKeywords();
//                    	arrayList = kw.setKeywords(asmk);
//                        autocomplete = new AutoComplete(this, arrayList);
//                        textArea.getParent().addEventHandler(KeyEvent.KEY_PRESSED, handler);
//                        hasListener = true;
//                        break;
//                }
//            }
//        }
//    }
//
//
//
//
	// Make the TextArea available to the autocomplete handler
  
//	
//	


//	public TextArea getTextArea() {
//		return textArea;
//	}

//    public String getText() {
//        return textProperty().get();
//    }
//
//    public void setText(String value) {
//        textProperty().set(value);
//    }
//
//    public StringProperty textProperty() {
//        return textArea.textProperty();
//    }
//

//	public void setTextArea(TextArea textArea) {
//		this.textArea = textArea;
//	}


//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}
}
