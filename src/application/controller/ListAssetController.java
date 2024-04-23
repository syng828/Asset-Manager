package application.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.Asset;
import mapping.AssetHandler;

public class ListAssetController {
	
	
	private static final String String = null;
	@FXML TextField searchBar;
	@FXML Button searchBtn;
	@FXML TextField assetField1;
	@FXML TableView<Asset> table;
	@FXML TableColumn<Asset, String> nameCol;
	@FXML TableColumn categoryCol;
	@FXML TableColumn locationCol;
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
			System.out.println("Found Asset: " + ast.getName() + " Category: " + ast.getCategory());
			
		}
		//table = new TableView<Asset>();
		//Asset a1 = a.get(0);
		//nameCol = new TableColumn<Asset,String>("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("category"));
		locationCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("location"));
	
		table.getColumns().clear();
		table.getItems().clear();
		table.getColumns().add(nameCol);
		table.getColumns().add(categoryCol);
		table.getColumns().add(locationCol);
		//table.getItems().add(a1);
		for(Asset ast: a ) {
			table.getItems().add(ast);
			
		}
		
	}
	
	//deletes asset
	@FXML public void deleteAsset() { 
		if (table.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Asset Selected");
		    alert.setContentText("Please select an asset."); 
		    alert.showAndWait();
		} else { 
			// Implement delete function
			Asset asset = table.getSelectionModel().getSelectedItem();
			AssetHandler.deleteAsset(asset);
			table.getItems().remove(asset); 
			System.out.println("Deleted Asset: " + table.getSelectionModel().getSelectedItem().getName());
		}
	}
}
