package controller;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import texteditor.TextEditor;
import texteditor.UI;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class SwingFx extends Application {

    @Override
    public void start (Stage stage) {
         SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
       
        

        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 800, 800));
        stage.show();
        }

    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	UI editor=new UI();
            	editor.setVisible(true);
               swingNode.setContent(editor);
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}