package application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

public class BrowseLocationController {
	
	@FXML ChoiceBox<String> lDrop;
	private HashMap<String, Integer> lMap;
	@FXML TableView<Asset> locTable;
	@FXML TableColumn<Asset, String> aCol;
	@FXML TableColumn <Asset, String> cCol;
	@FXML TableColumn <Asset, String> lCol;
	@FXML Button searchBtn;
	
	private void initLocationDropDown()  { 
		lMap = TagHandler.getLocationMap();
		System.out.print("Key Set is false? : " + lMap.keySet().isEmpty());
		lDrop.getItems().addAll(lMap.keySet());
	}
	
	public void initialize() { 
		TagHandler.initMap();
		initLocationDropDown();
	}
	
	public void displayLocTable() {
		if (lDrop.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Location Selected");
		    alert.setContentText("Please select a location."); 
		    alert.showAndWait();
		} else { 
			String loc = lDrop.getSelectionModel().getSelectedItem();
			ArrayList<Asset> a = AssetHandler.locSearch(loc);
			
			System.out.println("A empty? " + a.isEmpty());
			for(Asset ast: a ) {
				System.out.println(ast.getInputString());
				
			}
			//sets values with the specified attributes
			aCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name")); //gets property from assets
			cCol.setCellValueFactory(cellData -> {
			    String categoryName = cellData.getValue().getCategoryName(); //creates a special string property from category name for each asset
			    return new SimpleStringProperty(categoryName);});  
			lCol.setCellValueFactory(cellData -> {
			    String locationName = cellData.getValue().getLocationName();
			    return new SimpleStringProperty(locationName);}); 
		
			locTable.getColumns().clear();
			locTable.getItems().clear();
			locTable.getColumns().add(aCol);
			locTable.getColumns().add(cCol);
			locTable.getColumns().add(lCol);
			     
			for(Asset ast: a ) {
				locTable.getItems().add(ast);
			}
		}
	}
	
}
