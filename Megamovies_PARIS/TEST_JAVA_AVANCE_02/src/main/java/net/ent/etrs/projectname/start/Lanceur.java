package net.ent.etrs.projectname.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.projectname.view.references.Screens;
import net.ent.etrs.projectname.view.utils.FxmlUtils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory("PU_DC").createEntityManager();

        primaryStage.setTitle("Mega Movies");
        FxmlUtils.chargerScene(Screens.ACCUEIL);
        Scene scene = FxmlUtils.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
