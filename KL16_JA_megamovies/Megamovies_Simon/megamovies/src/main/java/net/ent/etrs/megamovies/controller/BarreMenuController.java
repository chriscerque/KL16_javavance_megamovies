package net.ent.etrs.megamovies.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.ClickableMenu;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;



public class BarreMenuController {


    public MenuBar menu;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.accueil());
        this.menu.getMenus().add(0, clickableMenu);
    }
    @FXML
    private void accueil() {
        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);
    }
    @FXML
    public void listerFilms() {
        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_FILMS);
    }

    @FXML
    public void gererFilm() {
        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILMS);
    }
}
