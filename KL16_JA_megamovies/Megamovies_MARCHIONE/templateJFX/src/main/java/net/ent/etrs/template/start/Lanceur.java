package net.ent.etrs.template.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.template.view.references.Screens;
import net.ent.etrs.template.view.utils.FxmlUtils;


public final class Lanceur extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TEST JAVAFX");
        //primaryStage.getIcons().add(new Image("/img/.png"));

        FxmlUtils.chargerScene(Screens.ACCUEIL);

        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}