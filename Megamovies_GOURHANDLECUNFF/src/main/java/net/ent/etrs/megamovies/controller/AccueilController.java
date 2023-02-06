package net.ent.etrs.megamovies.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

public class AccueilController extends AbstractController {
    @FXML
    VBox barreMenu;

    public static void init() {
        System.out.println("init");
        try {
            FACADE_FILM.initialisationFilm();
            FACADE_REALISATEUR.initialisationRealisateur();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void initialize() {

    }
}
