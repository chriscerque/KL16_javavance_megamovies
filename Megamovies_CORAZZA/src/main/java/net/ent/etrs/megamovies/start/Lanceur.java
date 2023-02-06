package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Lanceur extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    //@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Megamovies");
		//AnchorPane root = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
