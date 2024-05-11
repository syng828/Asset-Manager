package application.controller;

//@TODO: Add a redirect back to home once successful

import java.io.IOException;
import java.net.URL;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; 
import mapping.AssetHandler;

//Controls Main.fxml for switching between pages
public class MainController { 
	
	@FXML HBox mainBox; 
	
	// Load Home.fxml when the controller is initialized
	 @FXML
    public void initialize() {
		if(AssetHandler.hasExpiredAssets()) {
			loadFXML("view/WarrantyWarning.fxml");
		}
		else {
			loadFXML("view/Home.fxml");
		}
        
    }
	 
	//Replaces display with the Home.fxml page when button is clicked
	@FXML public void goToHome() { 
		loadFXML("view/Home.fxml");
	}

	//Replaces display with the AddNewCategory.fxml page when button is clicked
	@FXML public void goToAddCategory() { 
		loadFXML("view/AddNewCategory.fxml"); 
	}
	
	//Replaces display with the AddNewLocation.fxml page when button is clicked
	@FXML public void goToAddLocation() { 
		loadFXML("view/AddNewLocation.fxml"); 
	}
	
	//Replaces display with the AddNewLocation.fxml page when button is clicked
	@FXML public void goToAddAsset() { 
		loadFXML("view/AddNewAsset.fxml");
	}
	
	@FXML public void goToEditAsset() {
		loadFXML("view/ListAsset.fxml");
	}
	
	@FXML public void goToBrowseCategory() {
		loadFXML("view/BrowseCategory.fxml");
	}
	
	@FXML public void goToBrowseLocation() {
		loadFXML("view/BrowseLocation.fxml");
	}
	
	@FXML public void goToExpiredAssets() { 
		loadFXML("view/ExpiredWarrantyAsset.fxml"); 
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