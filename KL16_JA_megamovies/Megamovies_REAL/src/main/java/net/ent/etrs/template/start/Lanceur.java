package net.ent.etrs.template.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.template.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.template.view.references.Screens;



public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("MegaMovies");

        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);
        Scene scene = FxmlUtils.getScene();
        stage.setScene(scene);
        stage.show();
    }

}
