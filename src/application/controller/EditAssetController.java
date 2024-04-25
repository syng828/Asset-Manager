package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

public class EditAssetController {

	@FXML AnchorPane editPane;
	@FXML TextField assetTextField;
	@FXML ChoiceBox<String> categoryDropDown;
	@FXML ChoiceBox<String> locationDropDown;
	@FXML DatePicker purchaseDatePicker;
	@FXML TextArea descriptionTextArea;
	@FXML DatePicker warrantyDatePicker;
	@FXML TextField purchasedTextField;
	@FXML Button editBtn;
	
	private Asset asset = AssetHandler.getSelectedAsset(); 
	private AssetHandler assetHandler = new AssetHandler(); 
	
	private HashMap<String, Integer> categoryMap;
	private HashMap<String, Integer> locationMap;
	
	//Populates the dropdown with the location and categories in the db 
	private void initCategoryDropDown()  { 
		categoryMap = TagHandler.getCategoryMap();
	    categoryDropDown.getItems().addAll(categoryMap.keySet());  
	}
	
	private void initLocationDropDown()  { 
		locationMap = TagHandler.getLocationMap();
		locationDropDown.getItems().addAll(locationMap.keySet());
	}
	
	public void initialize() {  //startup code 
		initCategoryDropDown();
		initLocationDropDown();
		
		String categoryName = TagHandler.getCategoryName(asset.getCategoryID()); 
		String locationName = TagHandler.getLocationName(asset.getLocationID()); 
		
		assetTextField.setText(asset.getName());
		categoryDropDown.setValue(categoryName);
		locationDropDown.setValue(locationName);
		
		if (asset.getPurchaseDate() != null) {
		    purchaseDatePicker.setValue(asset.getPurchaseDate().toLocalDate());
		}
		if (!asset.getDescription().equals("")) 
			descriptionTextArea.setText(asset.getDescription());
		
		if (asset.getWarrantyExpDate() != null) {
			warrantyDatePicker.setValue(asset.getWarrantyExpDate().toLocalDate());
		}
		purchasedTextField.setText(Integer.toString(asset.getPurchasedValue()));
	}
	 
	@FXML public void editAsset() { 
		//Returns an alert if the asset, category, or location is not specified. 
		if (assetTextField.getText().isEmpty() || categoryDropDown.getValue() == null || locationDropDown.getValue() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter the required fields."); 
			alert.showAndWait();
			return;
		}
		
		/*Creates an assetHandler, and edits an asset. The fields are updated */
		String name = assetTextField.getText();
		String category = categoryDropDown.getValue();
		String location = locationDropDown.getValue(); 
		
		String purchaseString; 
		String warrantyString;
		if (purchaseDatePicker.getValue()!= null)
			purchaseString = Date.valueOf(purchaseDatePicker.getValue()).toString();
		else 
			purchaseString = "NULL"; 
		
		if (warrantyDatePicker.getValue()!= null)
			warrantyString = Date.valueOf(warrantyDatePicker.getValue()).toString();
		else
			warrantyString = "NULL"; 
		
		String description = descriptionTextArea.getText(); 
		if (description.isEmpty())
				description = "";  
	
		int purchasedInteger = 0;
		try { 
			if (!purchasedTextField.getText().isEmpty()) { 
				purchasedInteger = Integer.parseInt(purchasedTextField.getText()); 
			}
		} catch (NumberFormatException e) { 
			 Alert alert = new Alert(Alert.AlertType.ERROR);
		     alert.setTitle("Invalid input");
		     alert.setContentText("Please enter a valid integer for purchased value."); 
		     alert.showAndWait();
		     return;
		}
			
		try { 
			int categoryID = categoryMap.get(category); 
			int locationID = locationMap.get(location);
			
			assetHandler.updateAsset("CategoryID", "'"+categoryID+"'", asset.getName());
			assetHandler.updateAsset("LocationID", "'"+locationID+"'", asset.getName());
			assetHandler.updateAsset("PurchaseDate", "'"+purchaseString+"'", asset.getName());
			assetHandler.updateAsset("Description", "'"+description+"'", asset.getName());
			assetHandler.updateAsset("PurchasedValue", Integer.toString(purchasedInteger), asset.getName());
			assetHandler.updateAsset("WarrantyExpDate", "'"+warrantyString+"'", asset.getName());
			assetHandler.updateAsset("Name", "'"+name+"'", asset.getName());
		} catch (SQLException e ) { 
		 Alert alert = new Alert(Alert.AlertType.ERROR);
	     alert.setTitle("Invalid input");
	     alert.setContentText(e.getMessage()); 
	     alert.showAndWait();
	     return;
	}
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Updated Asset:");
        alert.setContentText("Successfully updated Asset");
        alert.showAndWait();
        
	}
	
	@FXML public void goBackToList() { 
		loadFXML("view/ListAsset.fxml"); 
	}
	
	//Changes the view on the right
		private void loadFXML(String fxmlPath) { 
			HBox mainBox = (HBox) editPane.getParent();
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
