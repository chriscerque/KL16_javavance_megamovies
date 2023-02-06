package net.ent.etrs.megamovies_falgat.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import net.ent.etrs.megamovies_falgat.commons.utils.FxmlUtils;
import net.ent.etrs.megamovies_falgat.view.references.Screens;

public class BarreMenuController extends AbstractController{

    @FXML
    private MenuBar mbMenu;

    @FXML
    private MenuItem miLIsterFilm;

    @FXML
    private MenuItem miAjouterFilm;

    @FXML
    private MenuItem miQuitter;

    @FXML
    private MenuItem miAide;


    public void listerFilm(){

        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_FILMS, new ListerFilmsController());

    }

    public void ajouterFilm(){

        FxmlUtils.chargerScene(Screens.SCREEN_CREER_FILM, new CreerFilmController());

    }

    public void quitter(){

        Platform.exit();

    }

    public void aide(){

    }

}
