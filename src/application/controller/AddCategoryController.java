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
		//Adds category from text field to categories in database. 
		/* Create a category object, with the categoryTextField.getText() as the category
		 * Then call the
		 * TagHandler class to insert it into the database. Add exception if duplicate
		 * category found?
		 */
		
		String categoryName = categoryTextField.getText();
		
		Category category = new Category(categoryName);
		TagHandler tagHandler = new TagHandler();
		
		//Check if the category already exists in the db
		if (tagHandler.searchTag(category.getName(), category.getTableName()) != null ) {
			Alert existed = new Alert(Alert.AlertType.WARNING);
			existed.setTitle("Category already exists");
			existed.setContentText("Please enter new category name");
			existed.showAndWait();
			return;
		} 
		// Add Category into db
		tagHandler.addTag(category);
		
		// Show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Category added successfully.");
        alert.showAndWait();
        
        // Clear the text field
        categoryTextField.clear();
        
	}
	
}