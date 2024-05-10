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

public class BrowseCategoryController {
	
	@FXML AnchorPane pane;
	@FXML ChoiceBox<String> cDrop;
	private HashMap<String, Integer> cMap;
	@FXML TableView<Asset> catTable;
	@FXML TableColumn<Asset, String> aCol;
	//@FXML TableColumn <Asset, String> cCol;
	//@FXML TableColumn <Asset, String> lCol;
	@FXML Button searchBtn;
	@FXML Button examineBtn;
	
	private void initCategoryDropDown()  { 
		cMap = TagHandler.getCategoryMap();
		System.out.print("Key Set is false? : " + cMap.keySet().isEmpty());
		cDrop.getItems().addAll(cMap.keySet());
	}
	
	public void initialize() { 
		TagHandler.initMap();
		initCategoryDropDown();
	}
	
	@FXML public void examineAsset() { 
		if (catTable.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Asset Selected");
		    alert.setContentText("Please select an asset."); 
		    alert.showAndWait();
		} else { 
			Asset asset = catTable.getSelectionModel().getSelectedItem();
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
	
	
	public void displayCatTable() {
		if (cDrop.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Category Selected");
		    alert.setContentText("Please select a category."); 
		    alert.showAndWait();
		} else { 
			String loc = cDrop.getSelectionModel().getSelectedItem();
			int id = cMap.get(loc);
			String ids = String.valueOf(id);
			ArrayList<Asset> a = AssetHandler.catSearch(ids);
			
			System.out.println("A empty? " + a.isEmpty());
			for(Asset ast: a ) {
				System.out.println(ast.getInputString());
				
			}
			//sets values with the specified attributes
			aCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name")); //gets property from assets
			/*
			cCol.setCellValueFactory(cellData -> {
			    String categoryName = cellData.getValue().getCategoryName(); //creates a special string property from category name for each asset
			    return new SimpleStringProperty(categoryName);});  
			lCol.setCellValueFactory(cellData -> {
			    String locationName = cellData.getValue().getLocationName();
			    return new SimpleStringProperty(locationName);}); 
			*/
			catTable.getColumns().clear();
			catTable.getItems().clear();
			catTable.getColumns().add(aCol);
			//locTable.getColumns().add(cCol);
			//locTable.getColumns().add(lCol);
			     
			for(Asset ast: a ) {
				catTable.getItems().add(ast);
			}
		}
	}
	
	
}
