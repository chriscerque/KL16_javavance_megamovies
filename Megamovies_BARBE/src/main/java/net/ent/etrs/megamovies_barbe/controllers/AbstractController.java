package net.ent.etrs.megamovies_barbe.controllers;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies_barbe.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierFilmImpl;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierRealisateurImpl;
import net.ent.etrs.megamovies_barbe.view.references.ConstantesView;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractController {

    protected static final FacadeMetierFilm FACADE_METIER_FILM;
    protected static final FacadeMetierRealisateur FACADE_METIER_REALISATEUR;

    static {
        FACADE_METIER_FILM = new FacadeMetierFilmImpl();
    }

    static {
        FACADE_METIER_REALISATEUR = new FacadeMetierRealisateurImpl();
    }


    protected void chargerScene(Scene sceneCourante, String screen, Object controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
        if (!Objects.isNull(controller)) {
            loader.setController(controller);
        }
        try {
            sceneCourante.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page.", Alert.AlertType.ERROR);
        }
    }

    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstantesView.CONFIRMATION_TITRE, ConstantesView.CONFIRMATION_HEADER,
                ConstantesView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            AlerteUtils.afficherMessageDansAlerte("Sortie de l'application.", Alert.AlertType.INFORMATION);
            Platform.exit();
        }
    }

    protected void aPropos() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by Barbe :)", Alert.AlertType.INFORMATION);
    }

}
