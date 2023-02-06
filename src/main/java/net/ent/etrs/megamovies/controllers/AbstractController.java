package net.ent.etrs.megamovies.controllers;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies.commons.utils.jxf.AlerteUtils;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFactory;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.view.references.ConstanteView;

public abstract class AbstractController {
    protected static FacadeMetierFilm facadeMetierFilm;
    protected static FacadeMetierRealisateur facadeMetierRealisateur;

    public AbstractController() {
        try {
            facadeMetierFilm = FacadeMetierFactory.fabriquerFacadeMetier();
            facadeMetierRealisateur = FacadeMetierFactory.fabriquerFacadeMetierRealisateur();
//            facadeMetierMarque.initialiser();
//            facadeMetierVoiture.initialiser();
        } catch (Exception e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.INFORMATION);
        }

    }


    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstanteView.CONFIRMATION_DIALOG, ConstanteView.CONFIRMATION,
                ConstanteView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

    public void aPropos() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by CQA", Alert.AlertType.INFORMATION);
    }

}
