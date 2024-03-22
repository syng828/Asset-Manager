package application.controller;


import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.TagHandler;



public class AddCategoryController { 
	
	@FXML Button createCategoryBtn; 
	@FXML TextField categoryTextField;
	
	public void createCategory() { 
		if (categoryTextField.getText().isEmpty()) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter a category name"); 
			alert.showAndWait();
		}
		else {
			TagHandler.addCategory(categoryTextField.getText());
			List<String> categories = TagHandler.returnCategories(); 
			for (String category : categories) {
				System.out.println(category);
			}
		} 
	}
	
}