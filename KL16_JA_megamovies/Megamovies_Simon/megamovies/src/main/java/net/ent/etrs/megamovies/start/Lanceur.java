package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.megamovies.view.references.Screens;

public class Lanceur extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mega Movies");

        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);

        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
