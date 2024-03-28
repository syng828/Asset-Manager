package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class Main extends Application {
	
	/*@TODO: Fix the database and make sure it works, then call it in the AddCategoryController
	and add AddLocationController */ 
	
	@Override
	public void start(Stage primaryStage) {
		try { 
			//Application begins here
			HBox root = (HBox)FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
			Scene scene = new Scene(root, 600, 400); 
			primaryStage.setScene(scene); 
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
