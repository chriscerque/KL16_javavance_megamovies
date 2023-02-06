package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.controller.AccueilController;

public class LanceurFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mega Movies");
        AccueilController.init();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
