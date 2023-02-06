package net.ent.etrs.megamovies.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.megamovies.view.references.Screens;

public class BarreMenuController extends AbstractController {
    @FXML
    private VBox laBarre;

    @FXML
    private MenuItem listerFilms;

    @FXML
    private MenuItem ajouterFilm;

    @FXML
    private MenuItem quitter;


    @FXML
    public void quitter() {

        super.quitter();


    }

    @FXML
    public void listerFilms() {

        FxmlUtils.chargerScene(laBarre.getScene(), Screens.SCREEN_LISTER_FILM, null);

    }

    @FXML
    public void ajouterFilm() {

        FxmlUtils.chargerScene(laBarre.getScene(), Screens.SCREEN_GERER_FILM, new GererFilmController(null));
    }

    @FXML
    public void apropos() {
        super.aPropos();
    }


}
