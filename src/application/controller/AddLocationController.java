package application.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.TagHandler;

public class AddLocationController {
	@FXML Button createLocationBtn; 
	@FXML TextField locationTextField;
	@FXML TextField decsriptionTextField;
	
	public void createCategory() { 
		//Return an error if the text field is empty
		if (locationTextField.getText().isEmpty()) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter a location name"); 
			alert.showAndWait();
		}
		//Adds location from text field to locations in database.
		else {
			TagHandler.addCategory(categoryTextField.getText());
			List<String> categories = TagHandler.returnCategories(); //loops through results for testing purposes.
			for (String category : categories) {
				System.out.println(category);
			}
		} 
	}
	
}