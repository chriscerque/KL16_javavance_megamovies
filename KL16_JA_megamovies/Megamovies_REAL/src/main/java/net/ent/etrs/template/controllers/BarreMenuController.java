package net.ent.etrs.template.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import net.ent.etrs.template.commons.utils.jxf.ClickableMenu;
import net.ent.etrs.template.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.template.view.references.Screens;

public class BarreMenuController {

    @FXML
    public MenuBar menu;

    @FXML
    private void initialize(){
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.retourAccueil());
        this.menu.getMenus().add(0, clickableMenu);
    }

    private void retourAccueil() {
        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);
    }

    public void listerFilm(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.SCREEN_LISTER_FILM);
    }

    public void ajouterFilm(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.SCREEN_CREER_FILM);
    }

    public void quitter(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.SCREEN_ACCUEIL);
    }
}
