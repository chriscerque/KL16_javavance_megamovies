package net.ent.etrs.megamovies_barbe.commons.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;

public final class AlerteUtils {

    private AlerteUtils() {
    }


    public static void afficherExceptionDansAlerte(Exception e, AlertType niveauAlerte) {
        Alert a = new Alert(niveauAlerte);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }


    public static Boolean afficherMessageDansAlerte(String msg, AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setContentText(msg);
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }


    public static Boolean afficherMessageDansAlerte(String titre, String msg, AlertType niveauAlerte) {
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


    public static Boolean afficherMessageDansAlerte(String titre, String header, String msg, AlertType niveauAlerte) {
        Boolean retour = false;
        Alert a = new Alert(niveauAlerte);
        a.setTitle(titre);
        a.setHeaderText(header);
        a.setContentText(msg);
        // Je recupere le panneau du dialog pour appliquer le style a la dialogbox
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStyleClass().add("myDialog");
        Optional<ButtonType> oBtnType = a.showAndWait();

        if (oBtnType.get().getButtonData().equals(ButtonType.OK.getButtonData())) {
            retour = true;
        }

        return retour;
    }


}
