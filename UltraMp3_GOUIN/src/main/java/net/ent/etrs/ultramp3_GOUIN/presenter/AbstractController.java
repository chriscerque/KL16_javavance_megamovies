package net.ent.etrs.ultramp3_GOUIN.presenter;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierCompositeur;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierFactory;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierMusique;

import net.ent.etrs.ultramp3_GOUIN.view.references.ConstanteView;
import net.ent.etrs.ultramp3_GOUIN.view.utils.AlerteUtils;

public abstract class AbstractController {
    //recupération des facades
    protected static FacadeMetierMusique facadeMetierMusique;
    protected static FacadeMetierCompositeur facadeMetierCompositeur;

    public AbstractController() {
            facadeMetierMusique = FacadeMetierFactory.fabriquerFacadeMetierMusique();
            facadeMetierCompositeur = FacadeMetierFactory.fabriquerFacadeMetierCompositeur();

    }


    @FXML
    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstanteView.CONFIRMATION_DIALOG, ConstanteView.CONFIRMATION,
                ConstanteView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

    public void aide() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by GUN", Alert.AlertType.INFORMATION);
    }

}
