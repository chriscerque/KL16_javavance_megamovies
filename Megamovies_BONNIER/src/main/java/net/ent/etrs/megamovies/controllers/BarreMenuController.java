package net.ent.etrs.megamovies.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;


public class BarreMenuController extends AbstractController {

    @FXML
    MenuBar menu;

    @FXML
    public void initialize() {

    }

    @FXML
    public void gererFilm() {
        FxmlUtils.chargerScene(Screens.GERER_FILM, new GererFilmController(null));
    }

    public void listerFilm(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.LISTER_FILM);
    }

    @FXML
    public void quitter() {

        super.quitter();


    }
}
