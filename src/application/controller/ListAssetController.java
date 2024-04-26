package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleStringProperty;
import mapping.Asset;
import mapping.AssetHandler;

public class ListAssetController {
	
	
	@FXML AnchorPane pane;
	@FXML TextField searchBar;
	@FXML Button searchBtn;
	@FXML TextField assetField1;
	@FXML TableView<Asset> table;
	@FXML TableColumn<Asset, String> nameCol;
	@FXML TableColumn <Asset, String> categoryCol;
	@FXML TableColumn <Asset, String> locationCol;

	public void searchAsset() {
		AssetHandler.cancelSelectedAsset();
		if(searchBar.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No asset entered");
			alert.setContentText("Please enter the required fields."); 
			alert.showAndWait();
			return;
		}
		String sub = searchBar.getText();
		
		ArrayList<Asset> a = AssetHandler.search(sub);
		for(Asset ast: a ) {
			System.out.println(ast.getInputString());
			
		}
		//sets values with the specified attributes
		nameCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name")); //gets property from assets
		categoryCol.setCellValueFactory(cellData -> {
		    String categoryName = cellData.getValue().getCategoryName(); //creates a special string property from category name for each asset
		    return new SimpleStringProperty(categoryName);});  
		locationCol.setCellValueFactory(cellData -> {
		    String locationName = cellData.getValue().getLocationName();
		    return new SimpleStringProperty(locationName);}); 
	
		table.getColumns().clear();
		table.getItems().clear();
		table.getColumns().add(nameCol);
		table.getColumns().add(categoryCol);
		table.getColumns().add(locationCol);
		
		for(Asset ast: a ) {
			table.getItems().add(ast);
		}
	}
		//navigates to edit page
		@FXML public void goToEdit() { 
			if (table.getSelectionModel().getSelectedItem() == null) { 
				Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setTitle("No Asset Selected");
			    alert.setContentText("Please select an asset."); 
			    alert.showAndWait();
			} else { 
				Asset asset = table.getSelectionModel().getSelectedItem();
				AssetHandler.selectAsset(asset);  //selects current asset to be used when editing
				loadFXML("view/EditAsset.fxml");
			}
		}
			
		//Changes the view on the right
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

	//deletes asset
	@FXML public void deleteAsset() { 
		Asset selectedAsset = table.getSelectionModel().getSelectedItem();
	    if (selectedAsset == null) { 
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("No Asset Selected");
	        alert.setContentText("Please select an asset."); 
	        alert.showAndWait();
	    } else { 
	        AssetHandler.deleteAsset(selectedAsset);
	        table.getItems().remove(selectedAsset); 
	        System.out.println("Deleted Asset: " + selectedAsset.getName());
	    }

	}
}
