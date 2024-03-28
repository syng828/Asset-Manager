package application.controller;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mapping.TagHandler;


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
		}
		//Adds category from text field to categories in database. 
		/* Create a category object, with the categoryTextField.getText() as the category
		 * Then call the
		 * TagHandler class to insert it into the database. Add exception if duplicate
		 * category found?
		 */
		/* else {
			TagHandler.addCategory(categoryTextField.getText());
			List<String> categories = TagHandler.returnCategories(); //loops through results for testing purposes.
			for (String category : categories) {
				System.out.println(category);
			}
		}  */
	}
	
}