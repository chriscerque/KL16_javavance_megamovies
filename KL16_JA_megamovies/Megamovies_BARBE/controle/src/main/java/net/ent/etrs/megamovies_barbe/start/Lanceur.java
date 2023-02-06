package net.ent.etrs.megamovies_barbe.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies_barbe.commons.utils.FxmlUtils;
import net.ent.etrs.megamovies_barbe.view.references.ConstantesView;
import net.ent.etrs.megamovies_barbe.view.references.Screens;


public final class Lanceur extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(ConstantesView.TITRE_APP);

        FxmlUtils.chargerScene(Screens.ACCUEIL);

        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
