package net.ent.etrs.megamovies.view.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.ent.etrs.megamovies.view.references.Screens;

import java.util.Optional;

public final class AlerteUtils {

    private AlerteUtils() {
    }


    public static void afficherExceptionDansAlerte(Exception e, Alert.AlertType niveauAlerte) {
        Alert a = new Alert(niveauAlerte);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }


    public static Boolean afficherMessageDansAlerte(String msg, Alert.AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setContentText(msg);
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }


    public static Boolean afficherMessageDansAlerte(String titre, String msg, Alert.AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setContentText(msg);
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }


    public static Boolean afficherMessageDansAlerte(String titre, String header, String msg, Alert.AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setHeaderText(header);
        a.setContentText(msg);
        // Je recupere le panneau du dialog pour appliquer le style a la dialogbox
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(AlerteUtils.class.getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }


    public static Boolean afficherMessageDansAlerte(String titre, String header, String msg, Alert.AlertType niveauAlerte, String cheminImg) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setHeaderText(header);
        a.setContentText(msg);
        // Je recupere le panneau du dialog pour appliquer le style a la dialogbox
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(AlerteUtils.class.getResource(Screens.SCREEN_APPLICATION_CSS).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");


        //TODO sout
        System.out.println("cheminImg : " + cheminImg);

        Image img;
        try {
            img = new Image(cheminImg, 100.0, 40.0, true, true);
        } catch (NullPointerException | IllegalArgumentException e) {

            img = new Image("/garage/view/resources/NULL.png");
        }

        // Creation d'un ImageView pour y mettre l'image
        ImageView iv = new ImageView();
        iv.setImage(img);

        // Insertion de l'imageView dans le header du dialogPane
        a.setGraphic(iv);

        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }

}
