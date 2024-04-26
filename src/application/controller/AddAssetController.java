package application.controller;

import java.util.HashMap;
import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

//Controls the AddNewAsset.fxml
public class AddAssetController { 
	
	@FXML TextField assetTextField;
	@FXML ChoiceBox<String> categoryDropDown;
	@FXML ChoiceBox<String> locationDropDown;
	@FXML DatePicker purchaseDatePicker;
	@FXML TextArea descriptionTextArea;
	@FXML DatePicker warrantyDatePicker;
	@FXML TextField purchasedTextField;
	@FXML Button createAssetBtn; 
	
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
		TagHandler.initMap();
		initCategoryDropDown();
		initLocationDropDown();
	}
	
	@FXML public void createAsset() { 
		//Returns an alert if the asset, category, or location is not specified. 
		if (assetTextField.getText().isEmpty() || categoryDropDown.getValue() == null || locationDropDown.getValue() == null) { 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing input");
			alert.setContentText("Please enter the required fields."); 
			alert.showAndWait();
			return;
		}
		
		/*Creates an assetHandler, and adds an asset. The addAsset method adds
		 the asset into the database. */
		String assetName = assetTextField.getText();
		String categoryName = categoryDropDown.getValue();
		String locationName = locationDropDown.getValue(); 
	
		String description = descriptionTextArea.getText();
		String purchasedValue = purchasedTextField.getText();
		
		Asset asset; 
		AssetHandler assetHandler = new AssetHandler();
		if(assetName != null) {
			int categoryID = categoryMap.get(categoryName); 
			int locationID = locationMap.get(locationName); 
			
			asset = new Asset(assetName, categoryID, locationID);
			
			if (purchaseDatePicker.getValue() != null) 
				asset.setPurchaseDate(Date.valueOf(purchaseDatePicker.getValue()));
			
			if (!description.isEmpty())
				asset.setDescription(description);
			
			if (warrantyDatePicker.getValue() !=null)
				asset.setWarrantyExpDate(Date.valueOf(warrantyDatePicker.getValue()));
			
			if (!purchasedValue.isEmpty()) { 
				try { 
					int purchasedInteger = Integer.parseInt(purchasedValue);
					asset.setPurchasedValue(purchasedInteger); 
				} catch (NumberFormatException e ) { 
					 Alert alert = new Alert(Alert.AlertType.ERROR);
				     alert.setTitle("Invalid input");
				     alert.setContentText("Please enter a valid integer for purchased value."); 
				     alert.showAndWait();
				     return;
				}
			}
			
			String results = assetHandler.addAsset(asset);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Notice:");
	        alert.setHeaderText(null);
	        alert.setContentText(results);
	        alert.showAndWait();
		}
		
		// Clear the inputs	
		assetTextField.clear();
		descriptionTextArea.clear();
		purchasedTextField.clear();
		
		categoryDropDown.setValue(null);
		locationDropDown.setValue(null);
		purchaseDatePicker.setValue(null);
		warrantyDatePicker.setValue(null);
	}
	
}