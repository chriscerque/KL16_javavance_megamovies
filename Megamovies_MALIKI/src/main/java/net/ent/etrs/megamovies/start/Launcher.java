package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("MEGAMOVIES");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BarreMenu.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
	

