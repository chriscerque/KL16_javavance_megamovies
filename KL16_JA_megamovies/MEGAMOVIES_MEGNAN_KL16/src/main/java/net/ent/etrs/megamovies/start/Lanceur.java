package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

public final class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Burger Queen");

        FxmlUtils.chargerScene(Screens.ACCUEIL);
        primaryStage.setScene(FxmlUtils.getScene());

        primaryStage.show();

    }
}