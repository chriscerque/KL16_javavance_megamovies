package net.ent.etrs.tmplatejvsfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import net.ent.etrs.tmplatejvsfx.view.references.Screens;
import net.ent.etrs.tmplatejvsfx.view.utils.ClickableMenu;
import net.ent.etrs.tmplatejvsfx.view.utils.FxmlUtils;


public class BarMenuController extends AbstractController{
    @FXML
    private MenuBar menu;

    @FXML
    public MenuItem listerFilm;
    @FXML
    public MenuItem gererFilm;

    @FXML
    public MenuItem miquitter;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.accueil());
        this.menu.getMenus().add(0, clickableMenu);
    }

    private void accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }

    public void ajouterFilm() {
        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILM, new GererFilmController());
    }

    public void listerLivre() {
        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_FILM);
    }

}
