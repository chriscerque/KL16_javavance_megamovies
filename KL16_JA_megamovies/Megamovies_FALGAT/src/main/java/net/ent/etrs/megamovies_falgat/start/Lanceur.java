package net.ent.etrs.megamovies_falgat.start;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ent.etrs.megamovies_falgat.commons.utils.FxmlUtils;
import net.ent.etrs.megamovies_falgat.controller.AccueilController;
import net.ent.etrs.megamovies_falgat.view.references.Screens;

public class Lanceur extends Application {

    //This method is called immediately after the Application class is loaded and constructed

    public static void main(String[] args) {
        //launch(args);
        Application.launch(args);
    }

    public void init() {
        System.out.println("Init de l'application");
    }

    //This method is called when the application should stop,
    //and provides a convenient place to prepare for application exit and destroy resources.
    public void stop() {
        System.out.println("Arret de l'application... par la méthode stop");
    }



    @Override
    //Le point d'entrée de toutes les applications JavaFX
    public void start(Stage primaryStage) {

        primaryStage.setTitle("MegaMovies");

        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL, new AccueilController());

        Scene scene = FxmlUtils.getScene();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
