package application.controller;


import java.io.IOException;
import java.net.URL;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; 

//Controls Main.fxml for switching between pages
public class MainController { 
	
	@FXML HBox mainBox; 
	
	// Load Home.fxml when the controller is initialized
	 @FXML
    public void initialize() {
        loadFXML("view/Home.fxml");
    }
	 
	//Replaces display with the Home.fxml page when button is clicked
	@FXML public void goToHome() { 
		loadFXML("view/Home.fxml");
	}

	//Replaces display with the AddNewCategory.fxml page when button is clicked
	@FXML public void goToAddCategory() { 
		loadFXML("view/AddNewCategory.fxml"); 
	}
	
	//Loads the display on the right.
	private void loadFXML(String fxmlPath) { 
		URL url = getClass().getClassLoader().getResource(fxmlPath); 
		AnchorPane panel;
		try {
			panel = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 1 ) //Removes right AnchorPane beforehand to avoid overlapping views
				mainBox.getChildren().remove(1); 
			
			mainBox.getChildren().add(panel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}