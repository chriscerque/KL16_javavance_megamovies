package net.ent.etrs.templatefx.start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.templatefx.view.references.Screens;
import net.ent.etrs.templatefx.view.utils.FxmlUtils;

public class Lanceur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
//        EntityManager em = Persistence.createEntityManagerFactory("pu-burger").createEntityManager();

        stage.setTitle("Mega Movies");
        FxmlUtils.chargerScene(Screens.ACCUEIL);

        Scene scene = FxmlUtils.getScene();
        stage.setScene(scene);
        stage.show();

    }
}
