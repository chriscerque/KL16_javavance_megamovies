package net.ent.etrs.megamovie.controllers;

import javafx.fxml.FXML;
import net.ent.etrs.megamovie.view.references.Screens;
import net.ent.etrs.megamovie.view.utils.FxmlUtils;

public class BarreMenuController extends AbstractController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void listerFilm() {
        FxmlUtils.chargerScene(Screens.LISTER_FILM);
    }


    @FXML
    public void quitter() {
        super.quitter();
    }

    @FXML
    public void about() {
        super.aPropos();
    }

    @FXML
    public void ajouterFilm() {
        FxmlUtils.chargerScene(Screens.FILM, new FilmController(null));
    }

    public void listerFilmParRea() {
        FxmlUtils.chargerScene(Screens.LISTER_FILM_PAR_REA);
    }
}
