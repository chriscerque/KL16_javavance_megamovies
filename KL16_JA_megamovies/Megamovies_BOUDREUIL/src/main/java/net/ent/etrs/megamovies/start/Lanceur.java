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
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("Mega Movies");

        FxmlUtils.chargerScene(Screens.ACCUEIL);

        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
