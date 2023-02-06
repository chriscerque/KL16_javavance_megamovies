package net.ent.etrs.megamovies.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

public class BarreMenuController extends AbstractController {
    @FXML
    private VBox barreMenu;

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

        FxmlUtils.chargerScene(barreMenu.getScene(), Screens.LISTER_FILMS, null);

    }

    @FXML
    public void ajouterFilm() {

        FxmlUtils.chargerScene(barreMenu.getScene(), Screens.GERER_FILM, new GererFilmController(null));
    }

    @FXML
    public void info() {
        super.info();
    }


}
