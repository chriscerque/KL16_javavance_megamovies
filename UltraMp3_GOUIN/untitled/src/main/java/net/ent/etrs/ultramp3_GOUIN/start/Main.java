package net.ent.etrs.ultramp3_GOUIN.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.ent.etrs.ultramp3_GOUIN.start.references.ConstanteMain;
import net.ent.etrs.ultramp3_GOUIN.view.references.Screens;
import net.ent.etrs.ultramp3_GOUIN.view.utils.AlerteUtils;

import java.io.IOException;


public final class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{

            primaryStage.setTitle(ConstanteMain.NOM_APPLICATION);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Screens.ACCUEIL));


            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (
        IOException e) {
            AlerteUtils.afficherMessageDansAlerte("Impossible de charger la page d'accueil.", Alert.AlertType.ERROR);
        }
    }
}
