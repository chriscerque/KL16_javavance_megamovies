package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Film");

        FxmlUtils.chargerScene(Screens.ACCUEIL);
        Scene scene = FxmlUtils.getScene();
        stage.setScene(scene);
        stage.show();
    }

}
