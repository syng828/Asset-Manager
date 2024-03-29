package application.controller;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.TagHandler;
import mapping.Category;



//Controls the AddNewCategory.fxml
public class AddCategoryController { 
	
	@FXML Button createCategoryBtn; 
	@FXML TextField categoryTextField;
	
	public void createCategory() { 
		//Return an error if the text field is empty
		if (categoryTextField.getText().isEmpty()) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter a category name"); 
			alert.showAndWait();
			return;
		}
		
		/*Creates a tagHandler of type Category, and adds the tag. The addTag method adds
		 the category into the database. */
		String categoryName = categoryTextField.getText();
		Category category;
		TagHandler tagHandler = new TagHandler();
		if(categoryName != null) {
	
			category = new Category(categoryName);
			String results = tagHandler.addTag(category);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Notice:");
	        alert.setHeaderText(null);
	        alert.setContentText(results);
	        alert.showAndWait();
		}
		
		// Clear the text field	
		categoryTextField.clear();  
	}
	
}