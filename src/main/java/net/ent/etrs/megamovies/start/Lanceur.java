package net.ent.etrs.megamovies.start;


import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.commons.utils.jxf.AlerteUtils;
import net.ent.etrs.megamovies.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.megamovies.view.references.Screens;

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


    //Depuis JAVA 8, plus besoin de main

    @Override
    //Le point d'entrée de toutes les applications JavaFX
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Mon garage");


//            FXMLLoader loader = new FXMLLoader(getClass().getResource(Screens.SCREEN_ACCUEIL));
//
//
//            primaryStage.setScene(new Scene(loader.load()));

            FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);
            primaryStage.setScene(FxmlUtils.getScene());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Impossible de charger la page d'accueil.", Alert.AlertType.ERROR);
        }


    }
}
