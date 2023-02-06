package net.ent.etrs.megamovie.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.ent.etrs.megamovie.view.Screens;

public class Lanceur extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MegaMovies");


        AnchorPane root = FXMLLoader.load(Lanceur.class.getResource(Screens.Accueil));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
