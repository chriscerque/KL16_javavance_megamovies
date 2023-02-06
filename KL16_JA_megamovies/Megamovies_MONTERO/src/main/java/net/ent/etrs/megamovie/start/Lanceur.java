package net.ent.etrs.megamovie.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovie.controllers.FilmController;
import net.ent.etrs.megamovie.view.references.ConstantesView;
import net.ent.etrs.megamovie.view.references.Screens;
import net.ent.etrs.megamovie.view.utils.FxmlUtils;


public final class Lanceur extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(ConstantesView.TITLE);

        FxmlUtils.chargerScene(Screens.FILM, new FilmController(null));

        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
