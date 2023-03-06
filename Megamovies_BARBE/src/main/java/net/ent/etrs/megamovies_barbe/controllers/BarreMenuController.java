package net.ent.etrs.megamovies_barbe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.ent.etrs.megamovies_barbe.commons.utils.ClickableMenu;
import net.ent.etrs.megamovies_barbe.commons.utils.FxmlUtils;
import net.ent.etrs.megamovies_barbe.view.references.Screens;


public class BarreMenuController extends AbstractController {

    @FXML
    private MenuBar menu;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenuAccueil = new ClickableMenu("Accueil");
        clickableMenuAccueil.setOnAction(e -> this.accueil());
        this.menu.getMenus().add(0, clickableMenuAccueil);
    }


    public void accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }


    @FXML
    public void quitter() {
        super.quitter();
    }


    @FXML
    public void aPropos() {
        super.aPropos();
    }

    public void listerFilms(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.LISTER_FILMS, new listerFilmController());

    }

    public void ajouterFilms(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.AJOUTER_FILMS, new ajouterFilmController(null));

    }
}
