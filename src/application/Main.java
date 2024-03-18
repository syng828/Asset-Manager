package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mapping.TagHandler;

public class Main extends Application {
	Stage window; 
	Scene mainScene, addCategoryScene; 
	
	@Override
	public void start(Stage primaryStage) {
		try { 
			//Home Components
			window = primaryStage; 
			Label lblHome = new Label("Welcome to the homepage!");
			Button btnAddCategory = new Button("Add New Category");
			btnAddCategory.setOnAction(e -> window.setScene(addCategoryScene));
			
			//Add Category Components 
			Button btnHome = new Button("Return Home"); 
			btnHome.setOnAction(e -> window.setScene(mainScene));
			Label lblCategory = new Label("Name"); 
			TextField txtCategory = new TextField(); 
			txtCategory.setPromptText("name");
			Button btnCreateCategory = new Button("Create");  
			
			btnCreateCategory.setOnAction(new EventHandler<ActionEvent>() { 
				@Override 
				public void handle(ActionEvent event) { 
					if (txtCategory.getText().isEmpty()) { 
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Missing input");
						alert.setContentText("Please enter a category name"); 
						alert.showAndWait();
					}
					else {
						TagHandler.addCategory(txtCategory.getText());
					} 
				}
			}); 
			
			Button btnRetrieveCategories = new Button("Retrieve"); 
			btnRetrieveCategories.setOnAction(e -> System.out.println(TagHandler.returnCategories()));
			
			//Home Scene
			VBox layoutHome = new VBox(20); 
			layoutHome.getChildren().addAll(lblHome, btnAddCategory);
			mainScene = new Scene(layoutHome, 200, 200); 
			
			//Add Category Scene
			VBox navigation = new VBox(20); 
			navigation.getChildren().addAll(btnHome); 
			VBox addCategoryField = new VBox(20); 
			addCategoryField.getChildren().addAll(lblCategory, txtCategory, btnCreateCategory, btnRetrieveCategories); 
			HBox layoutAddCategory = new HBox(20); 
			layoutAddCategory.getChildren().addAll(navigation, addCategoryField); 
			addCategoryScene = new Scene(layoutAddCategory, 200, 200); 
			
			window.setScene(mainScene); 
			window.setTitle("Asset Tracker");
			window.show(); 
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
