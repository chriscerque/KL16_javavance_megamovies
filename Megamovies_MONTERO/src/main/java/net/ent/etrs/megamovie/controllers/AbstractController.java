package net.ent.etrs.megamovie.controllers;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovie.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovie.model.facades.impl.FacadeMetierFactory;
import net.ent.etrs.megamovie.view.references.ConstantesView;
import net.ent.etrs.megamovie.view.utils.AlerteUtils;

public abstract class AbstractController {

    protected static final FacadeMetierFilm FACADE_METIER_FILM;
    protected static final FacadeMetierRealisateur FACADE_METIER_REALISATEUR;

    static {
        FACADE_METIER_FILM = FacadeMetierFactory.fabriquerFmFilm();
        FACADE_METIER_REALISATEUR = FacadeMetierFactory.fabriquerFmRealisateur();
    }


    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstantesView.CONFIRMATION_DIALOG, ConstantesView.CONFIRMATION,
                ConstantesView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            Platform.exit();
        }
    }


    protected void aPropos() {
        AlerteUtils.afficherMessageDansAlerte(Constantes.TITRE_APROPOS, Constantes.HEADER_APROPOS, Constantes.MSG_APROPOS, Alert.AlertType.INFORMATION);
    }
}
