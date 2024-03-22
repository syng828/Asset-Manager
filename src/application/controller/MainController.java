package application.controller;


import java.io.IOException;
import java.net.URL;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; 

public class MainController { 
	
	@FXML HBox mainBox; 
	
	@FXML public void goToHome() { 
		URL url = getClass().getClassLoader().getResource("view/Home.fxml"); 
		AnchorPane panel;
		try {
			panel = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 1 )
				mainBox.getChildren().remove(1); 
			
			mainBox.getChildren().add(panel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@FXML public void goToAddCategory() { 
		URL url = getClass().getClassLoader().getResource("view/AddNewCategory.fxml"); 
		AnchorPane panel;
		try {
			panel = (AnchorPane) FXMLLoader.load(url);
			if (mainBox.getChildren().size() > 1 )
				mainBox.getChildren().remove(1); 
			mainBox.getChildren().add(panel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}