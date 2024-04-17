package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import mapping.AssetHandler;
import mapping.TagHandler;

//Controls the AddNewAsset.fxml
public class AddAssetController { 
	
	@FXML TextField assetTextField;
	@FXML ChoiceBox<String> categoryDropDown;
	@FXML ChoiceBox<String> locationDropDown;
	@FXML DatePicker purchaseDatePicker;
	@FXML TextArea descriptionTextArea;
	@FXML DatePicker warrantyDateDropDown;
	@FXML TextField purchasedTextField;
	@FXML Button createAssetBtn; 

	
	private void initCategoryDropDown()  { 
		TagHandler.getCategoryList();
	}
	
	private void initLocationDropDown()  { 
		
	}
	
	public void initialize() {  //startup code 
		initCategoryDropDown();
		initLocationDropDown();
	}
	
	@FXML public void createAsset() { 
		//if (assetTextField.getText().isEmpty() || categoryDropDown.)
	}
	
}