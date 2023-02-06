package net.ent.etrs.megamovies_falgat.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import net.ent.etrs.megamovies_falgat.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeFilm;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeRealisateur;
import net.ent.etrs.megamovies_falgat.model.facades.impl.FacadeMetierFactory;
import net.ent.etrs.megamovies_falgat.view.references.ConstanteView;


public abstract class AbstractController {
    protected static FacadeFilm facadeFilm;
    protected static FacadeRealisateur facadeRealisateur;

    public AbstractController() {
            facadeFilm = FacadeMetierFactory.fabriquerFacadeFilm();
            facadeRealisateur = FacadeMetierFactory.fabriquerFacadeRealisateur();


    }


    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstanteView.CONFIRMATION_DIALOG, ConstanteView.CONFIRMATION,
                ConstanteView.CONFIRMATION_QUITTER, AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

    public void aPropos() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by CQA", AlertType.INFORMATION);
    }

}
