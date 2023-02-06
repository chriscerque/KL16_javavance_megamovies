package net.ent.etrs.Megamovies_SANTOS.model.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.Screens;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.utils.FxmlUtils;


public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MEGAMOVIES");
        FxmlUtils.chargerScene(Screens.ACCUEIL);
        Scene scene = FxmlUtils.getScene();
        stage.setScene(scene);
        stage.show();

    }
}
