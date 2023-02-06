package net.ent.etrs.megamovie.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.ent.etrs.megamovie.view.Screens;
import net.ent.etrs.megamovie.view.utils.FxmlUtils;

public class AccueilController {

@FXML
    public Button accueilNouveau;
    @FXML
    public Button accueilList;

    @FXML
    public void initialize(){

    }


    public void accueilLister(ActionEvent actionEvent){
        FxmlUtils.chargerScene(Screens.Liste, new ListeController());
    }

    public void   accueilCreer(ActionEvent actionEvent)   {
        FxmlUtils.chargerScene(Screens.Ajout, new AjoutController());
    }
}
