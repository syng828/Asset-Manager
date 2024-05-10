package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mapping.Asset;
import mapping.AssetHandler;
import mapping.TagHandler;

public class ExamineAssetController {
	@FXML AnchorPane editPane;
	@FXML Label nameLabel;
	@FXML Label catLabel;
	@FXML Label locLabel;
	@FXML DatePicker pdDatePicker;
	@FXML Label descLabel;
	@FXML Label pvLabel;
	@FXML DatePicker wepDatePicker;
	
	private Asset asset = AssetHandler.getSelectedAsset(); 
	private AssetHandler assetHandler = new AssetHandler(); 
	
	public void initialize() {
		nameLabel.setText(asset.getName());
		catLabel.setText(asset.getCategoryName());
		locLabel.setText(asset.getLocationName());
		
		if (asset.getPurchaseDate() != null) {
		    pdDatePicker.setValue(asset.getPurchaseDate().toLocalDate());
		}
		if (!asset.getDescription().equals("")) 
			descLabel.setText(asset.getDescription());
		
		if (asset.getWarrantyExpDate() != null) {
			wepDatePicker.setValue(asset.getWarrantyExpDate().toLocalDate());
		}
		
		pvLabel.setText(Integer.toString(asset.getPurchasedValue()));
	}
}
