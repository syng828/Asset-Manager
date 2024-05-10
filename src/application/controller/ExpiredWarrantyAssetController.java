package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mapping.Asset;
import mapping.AssetHandler;

public class ExpiredWarrantyAssetController {
	@FXML AnchorPane pane;
	@FXML TableView<Asset> table;
	@FXML TableColumn<Asset, String> nameCol;
	@FXML TableColumn<Asset, String> warrantyCol; 
	
	ArrayList<Asset> a;
	public void initialize() { 
		AssetHandler.cancelSelectedAsset();
		a = AssetHandler.getExpiredAssetList();
		nameCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name"));
		
		warrantyCol.setCellValueFactory(cellData -> {
		    Date warrantyExpDate = cellData.getValue().getWarrantyExpDate(); //converts the date to string
		    return new SimpleStringProperty(warrantyExpDate.toString());});  
	
		table.getColumns().clear();
		table.getItems().clear();
		table.getColumns().add(nameCol);
		table.getColumns().add(warrantyCol);
		
		for(Asset ast: a ) {
			table.getItems().add(ast);
		}
	}
	
	@FXML public void goToView() { 
		if (table.getSelectionModel().getSelectedItem() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setTitle("No Asset Selected");
		    alert.setContentText("Please select an asset."); 
		    alert.showAndWait();
		} else { 
			Asset asset = table.getSelectionModel().getSelectedItem();
			AssetHandler.selectAsset(asset);  //selects current asset to be used when viewing
			loadFXML("view/AssetInfo.fxml");
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
}
