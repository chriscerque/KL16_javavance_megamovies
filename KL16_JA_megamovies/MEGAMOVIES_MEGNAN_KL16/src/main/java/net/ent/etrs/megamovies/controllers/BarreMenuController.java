package net.ent.etrs.megamovies.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.megamovies.view.utils.ClickableMenu;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

public class BarreMenuController {

    @FXML
    private MenuBar menu;

    @FXML
    public void initialize() {

        //Accueil
        ClickableMenu clickableAccueil = new ClickableMenu("Accueil");
        clickableAccueil.setOnAction(e -> this.Accueil());
        this.menu.getMenus().add(0, clickableAccueil);

        //Accueil
        ClickableMenu clickableAide = new ClickableMenu("Aide");
        clickableAide.setOnAction(e -> this.Aide());
        this.menu.getMenus().add(2, clickableAide);

    }

    public void Accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }

    public void ListerMovies() {
        FxmlUtils.chargerScene(Screens.LISTER_MOVIES);
    }

    public void AjouterMovies() {
        FxmlUtils.chargerScene(Screens.AJOUTER_MOVIES);
    }

    public void Aide() {
        FxmlUtils.chargerScene(Screens.AIDE);
    }

    public void quitter() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }
}
