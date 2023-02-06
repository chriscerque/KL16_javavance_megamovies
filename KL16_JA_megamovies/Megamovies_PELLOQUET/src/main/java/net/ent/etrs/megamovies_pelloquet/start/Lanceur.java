package net.ent.etrs.megamovies_pelloquet.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies_pelloquet.controller.OuvertureController;
import net.ent.etrs.megamovies_pelloquet.start.references.ConstantesLanceur;
import net.ent.etrs.megamovies_pelloquet.view.utils.Screens;

public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(ConstantesLanceur.TITRE);
        OuvertureController.init();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Screens.SCREEN_ACCUEIL));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
