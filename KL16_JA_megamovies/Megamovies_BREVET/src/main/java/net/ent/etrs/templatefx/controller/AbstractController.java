package net.ent.etrs.templatefx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.ent.etrs.templatefx.model.facades.FacadeMetierFactory;
import net.ent.etrs.templatefx.model.facades.FacadeMetierFilm;
import net.ent.etrs.templatefx.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.templatefx.view.references.ConstantesView;
import net.ent.etrs.templatefx.view.references.Screens;
import net.ent.etrs.templatefx.view.utils.AlerteUtils;
import net.ent.etrs.templatefx.view.utils.FxmlUtils;

public abstract class AbstractController {

    protected static final FacadeMetierRealisateur fmr = FacadeMetierFactory.getFacadeMetierRealisateur();
    ;
    protected static final FacadeMetierFilm fmf = FacadeMetierFactory.getFacadeMetierFilm();

    @FXML
    protected void accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }

    @FXML
    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstantesView.CONFIRMATION_DIALOG, ConstantesView.CONFIRMATION,
                ConstantesView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            Platform.exit();
        }
    }
}
