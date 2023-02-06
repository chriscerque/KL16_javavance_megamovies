package net.ent.etrs.megamovies.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facades.impl.FacadeMetierFilmImpl;
import net.ent.etrs.megamovies.view.C_VIEW;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;


public abstract class AbstractController {

    protected static final FacadeMetierFilm FACADE_METIER;

    static {
        FACADE_METIER = new FacadeMetierFilmImpl();
    }


    @FXML
    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(C_VIEW.CONFIRMATION_DIALOG, C_VIEW.CONFIRMATION,
                C_VIEW.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }


}
