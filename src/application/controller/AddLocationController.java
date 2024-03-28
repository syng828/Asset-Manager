package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.TagHandler;

public class AddLocationController {
	@FXML Button createLocationBtn; 
	@FXML TextField locationTextField;
	@FXML TextField descriptionTextField;
	
	public void createLocation() { 
		//Return an error if the text field is empty
		if (locationTextField.getText().isEmpty()) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter a location name"); 
			alert.showAndWait();
		}
		/* 
		 * Create a Location object, with the locationTextField.getText() as the location,
		 * and the descriptionTextField.getText() as the description. Then call the
		 * TagHandler class to insert it into the database. Add exception if duplicate
		 * location found?
		 */
		//Adds location from text field to locations in database.
		/* else {
			TagHandler.addCategory(categoryTextField.getText());
			List<String> categories = TagHandler.returnCategories(); //loops through results for testing purposes.
			for (String category : categories) {
				System.out.println(category);
			}
		}  */
	}
	
}