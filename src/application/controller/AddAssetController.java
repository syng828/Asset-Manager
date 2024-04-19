package application.controller;

import java.util.ArrayList;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.Category;
import mapping.TagHandler;

//Controls the AddNewAsset.fxml
public class AddAssetController { 
	
	@FXML TextField assetTextField;
	@FXML ChoiceBox<String> categoryDropDown;
	@FXML ChoiceBox<String> locationDropDown;
	@FXML DatePicker purchaseDatePicker;
	@FXML TextArea descriptionTextArea;
	@FXML DatePicker warrantyDateDropDown;
	@FXML TextField purchasedTextField;
	@FXML Button createAssetBtn; 

	//Populates the dropdown with the location and categories in the db 
	private void initCategoryDropDown()  { 
		ArrayList<String> categories = TagHandler.getCategoryList();
		ObservableList<String> categoryOptions = FXCollections.observableArrayList(categories);
	    categoryDropDown.setItems(categoryOptions);
	}
	
	private void initLocationDropDown()  { 
		ArrayList<String> locations = TagHandler.getLocationList();
		ObservableList<String> locationOptions = FXCollections.observableArrayList(locations);
	    locationDropDown.setItems(locationOptions);
	}
	
	public void initialize() {  //startup code 
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
		LocalDate purchaseDate = purchaseDatePicker.getValue();
		String description = descriptionTextArea.getText(); 
		LocalDate warrantyExpDate = warrantyDateDropDown.getValue(); 
		String purchasedValue = purchasedTextField.getText();
		
		Asset asset; 
		AssetHandler assetHandler = new AssetHandler();
		if(assetName != null) {
			asset = new Asset(assetName, categoryName, locationName);
			
			if (purchaseDate != null) 
				asset.setPurchaseDate(java.sql.Date.valueOf(purchaseDate));
			if (!description.isEmpty())
				asset.setDescription(description);
			if (warrantyExpDate !=null)
				asset.setWarrantyExpDate(java.sql.Date.valueOf(warrantyExpDate));
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
		warrantyDateDropDown.setValue(null);
	}
	
}