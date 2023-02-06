package net.ent.etrs.megamovies.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

public class AccueilController extends AbstractController {

    @FXML
    VBox barreMenu;



    @FXML
    public void initialize() {

    }

    @FXML
    public void setBarreMenu(){
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_ACCUEIL, null);
    }


}
