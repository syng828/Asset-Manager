package application.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mapping.Asset;
import mapping.AssetHandler;

public class ListAssetController {
	
	
	@FXML TextField searchBar;
	@FXML Button searchBtn;
	@FXML TextField assetField1;
	//@FXML TextField assetField2;
	//@FXML TextField assetField3;
	
	public void searchAsset() {
		if(searchBar.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No asset entered");
			alert.setContentText("Please enter the required fields."); 
			alert.showAndWait();
			return;
		}
		String sub = searchBar.getText();
		AssetHandler ah = new AssetHandler();
		
		ArrayList<Asset> a = AssetHandler.search(sub);
		for(Asset ast: a ) {
			System.out.println("Found Asset: " + ast.getName());
			
		}
		Asset ast = a.get(0);
		assetField1.setText(ast.getName());
	
		
	}
}
