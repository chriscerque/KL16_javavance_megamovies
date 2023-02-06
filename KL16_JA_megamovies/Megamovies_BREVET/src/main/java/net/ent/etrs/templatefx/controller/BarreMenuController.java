package net.ent.etrs.templatefx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.templatefx.view.references.Screens;
import net.ent.etrs.templatefx.view.utils.ClickableMenu;
import net.ent.etrs.templatefx.view.utils.FxmlUtils;

public class BarreMenuController extends AbstractController {

    @FXML
    public MenuBar menu;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> super.accueil());
        this.menu.getMenus().add(0, clickableMenu);
    }

    public void creerFilm(final ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.AJOUTER_FILM, new AjouterFilmController(null));
    }

    public void listerFilm(final ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.LISTER_FILM);
    }
}
