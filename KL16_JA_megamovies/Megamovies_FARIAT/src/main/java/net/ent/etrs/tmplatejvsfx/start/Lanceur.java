package net.ent.etrs.tmplatejvsfx.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.tmplatejvsfx.view.references.Screens;
import net.ent.etrs.tmplatejvsfx.view.utils.FxmlUtils;

public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
//        EntityManager em = Persistence.createEntityManagerFactory("PU_DC").createEntityManager();

        primaryStage.setTitle("Ma première application JavaFx");
        FxmlUtils.chargerScene(Screens.ACCUEIL);
        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
