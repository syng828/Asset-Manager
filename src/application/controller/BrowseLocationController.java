package application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

public class BrowseLocationController {
	
	@FXML AnchorPane pane;
	@FXML ChoiceBox<String> lDrop;
	private HashMap<String, Integer> lMap;
	@FXML TableView<Asset> locTable;
	@FXML TableColumn<Asset, String> aCol;
	@FXML TableColumn <Asset, String> lCol;
	@FXML TableColumn <Asset, String> wCol;
	@FXML Button searchBtn;
	@FXML Button examineBtn;
	
	private void initLocationDropDown()  { 
		lMap = TagHandler.getLocationMap();
		System.out.print("Key Set is false? : " + lMap.keySet().isEmpty());
		lDrop.getItems().addAll(lMap.keySet());
	}
	
	public void initialize() { 
		TagHandler.initMap();
		initLocationDropDown();
	}
	
	@FXML public void examineAsset() { 
		if (locTable.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Asset Selected");
		    alert.setContentText("Please select an asset."); 
		    alert.showAndWait();
		} else { 
			Asset asset = locTable.getSelectionModel().getSelectedItem();
			AssetHandler.selectAsset(asset);  //selects current asset to be used when editing
			loadFXML("view/AssetInfo.fxml");
		}
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
	
	
	public void displayLocTable() {
		if (lDrop.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Location Selected");
		    alert.setContentText("Please select a location."); 
		    alert.showAndWait();
		} else { 
			String loc = lDrop.getSelectionModel().getSelectedItem();
			int id = lMap.get(loc);
			String ids = String.valueOf(id);
			ArrayList<Asset> a = AssetHandler.locSearch(ids);
			
			System.out.println("A empty? " + a.isEmpty());
			for(Asset ast: a ) {
				System.out.println(ast.getInputString());
				
			}
			//sets values with the specified attributes
			aCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name")); //gets property from assets
			lCol.setCellValueFactory(cellData -> {
			    String locationName = cellData.getValue().getLocationName();
			    return new SimpleStringProperty(locationName);}); 
			wCol.setCellValueFactory(cellData -> {
			    Date expirationDate = cellData.getValue().getWarrantyExpDate(); //creates a special string property from category name for each asset
			    return new SimpleStringProperty(expirationDate.toString());});  
			
			locTable.getColumns().clear();
			locTable.getItems().clear();
			locTable.getColumns().add(aCol);
			locTable.getColumns().add(lCol);
			locTable.getColumns().add(wCol);
			     
			for(Asset ast: a ) {
				locTable.getItems().add(ast);
			}
		}
	}
	
	
}
