package application.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

public class AssetInfoController {
	@FXML Text assetText; 
	@FXML Text categoryText;
	@FXML Text locationText; 
	@FXML Text purchaseText; 
	@FXML Text descriptionText; 
	@FXML Text valueText; 
	@FXML Text warrantyText; 
	
	public void initialize() { 
		
		Asset asset = AssetHandler.getSelectedAsset();
		String categoryName = TagHandler.getCategoryName(asset.getCategoryID()); 
		String locationName = TagHandler.getLocationName(asset.getLocationID()); 
		
		assetText.setText(AssetHandler.getSelectedAsset().getName());
		categoryText.setText(categoryName);
		locationText.setText(locationName);
		
		if (asset.getPurchaseDate() != null) {
			purchaseText.setText(asset.getPurchaseDate().toLocalDate().toString());
		}
		if (asset.getDescription() != null) {
			descriptionText.setText(asset.getDescription());
		} 
		if (asset.getWarrantyExpDate() != null) {
			warrantyText.setText(asset.getWarrantyExpDate().toLocalDate().toString());
		}
		valueText.setText(Integer.toString(asset.getPurchasedValue()));
	}
}
