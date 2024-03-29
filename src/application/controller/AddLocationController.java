package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.Location;
import mapping.TagHandler;

public class AddLocationController {
	@FXML Button createLocationBtn; 
	@FXML TextField locationTextField;
	@FXML TextField descriptionTextField;
	
	public void createLocation() { 
		//Return an error if the location field is empty. Description field is optional.
		if (locationTextField.getText().isEmpty()) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter a location name"); 
			alert.showAndWait();
		}
		/*Creates a tagHandler of type Location, and adds the tag. The addTag method adds
		 the category into the database. */
		else { 
			String locationName = locationTextField.getText();
			String description = descriptionTextField.getText();
			Location location;
			TagHandler tagHandler = new TagHandler();
			if(locationName != null) {
				if(description != null) {
					location = new Location(locationName, description);
				}
				else {
					location = new Location(locationName);
				}
				String results = tagHandler.addTag(location);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Notice:");
		        alert.setHeaderText(null);
		        alert.setContentText(results);
		        alert.showAndWait();
			}
		}
		locationTextField.clear();
		descriptionTextField.clear();
		
	}
	
}