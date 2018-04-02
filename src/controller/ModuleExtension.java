package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ModuleExtension extends Application
{
	
	private final static String [] arryayBase = {"RV32I", "RV64I"};
	private final static String [] arrayExtension = {"A", "C", "D", "F", "M", "N", "Q","V"};
	
	
	@FXML
	private Button Apply_button; // ID of button ok
	
	@FXML
	private Button cancel_button; // ID of button cancel
	
	@FXML
	private CheckBox M; // ID of M
	
	@FXML
	private CheckBox A; // ID of A
	
	@FXML
	private CheckBox F; // ID of F
	
	@FXML
	private CheckBox D; // ID of D
	
	@FXML
	private StackPane root; // ID of StackPane that holds everything
	
	@FXML
	private ChoiceBox<String> base_choice;  // ID of choice box
	
	
	@FXML
	private void handleM(ActionEvent e) 
	{
		if(M.isSelected() == true) 
		{
			System.out.println("Extension M is selected");
		}
		
	}
	
	@FXML
	private void handleA(ActionEvent e) 
	{
		if(A.isSelected() == true) 
		{
			System.out.println("Extension A is selected");
		}
		
	}
	
	@FXML
	private void handleF(ActionEvent e) 
	{
		if(F.isSelected() == true) 
		{
			System.out.println("Extension F is selected");
		}
		
	}
	
	@FXML
	private void handleD(ActionEvent e) 
	{
		if(D.isSelected() == true) 
		{
			System.out.println("Extension D is selected");
		}
		
	}
	
	@FXML
	private void handleButtonApply(ActionEvent e) 
	{
		handleM(e);
		handleA(e);
		handleF(e);
		handleD(e);
		getChoice(base_choice);
		root.getScene().getWindow().hide();
	}
	
	
	@FXML
	private void handleButtonCancel(ActionEvent e) 
	{
		/** set 32 base as  default **/
		base_choice.setValue("RV32I");
		getChoice(base_choice);
		root.getScene().getWindow().hide();
	}

	
	@FXML
	private void handleChoice(MouseEvent e) {
		base_choice.setValue("RV32I");
		base_choice.setItems(FXCollections.observableArrayList("RV32I","RV64I"));
		
	}

	public void getChoice(ChoiceBox<String> box)
	{
		String choice = box.getValue();
		System.out.println(choice);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@FXML
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	
		
	}
	
	public void main(String[] args) 
	{
		launch(args);
	}
	
	
}
