package application.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class WarrantyWarningController {
	
	@FXML AnchorPane pane;
	@FXML Button goHomeBtn;
	@FXML Button goToExpiredBtn;

	public void goHome() {
		loadFXML("view/Home.fxml");
	}
	
	public void goToExpired() {
		
		//Enter path to Expired Assets Page TODO
		loadFXML("view/Home.fxml");
	}
	
	private void loadFXML(String fxmlPath) { 
		HBox mainBox = (HBox) pane.getParent();
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
