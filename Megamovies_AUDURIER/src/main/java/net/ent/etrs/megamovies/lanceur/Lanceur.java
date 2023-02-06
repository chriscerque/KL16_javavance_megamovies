package net.ent.etrs.megamovies.lanceur;


import javafx.application.Application;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.controllers.GererFilmsController;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

public class Lanceur extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Megamovies");
        FxmlUtils.chargerScene(Screens.GERER_FILMS, new GererFilmsController(null));
        primaryStage.setScene(FxmlUtils.getScene());
        primaryStage.show();
    }
}
