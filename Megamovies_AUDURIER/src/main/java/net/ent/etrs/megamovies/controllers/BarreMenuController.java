package net.ent.etrs.megamovies.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

public class BarreMenuController extends AbstractController {

    @FXML
    private MenuBar menu;

    @FXML
    public void initialize() {
        FxmlUtils.createClickableMenu(menu, "Filmographie", 1).setOnAction(actionEvent -> this.filmo());
        FxmlUtils.createClickableMenu(menu, "Aide", 2).setOnAction(actionEvent -> this.aide());
    }

    private void filmo() {
        FxmlUtils.chargerScene(Screens.FILMO, new VoirFilmoController(null));
    }


    private void aide() {
        FxmlUtils.openSubScreen(menu.getScene(), "AIDE", Screens.AIDE, null);
    }


    public void quitter(final ActionEvent actionEvent) {
        ((Stage) menu.getScene().getWindow()).close();
    }

    public void listerFilms(final ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.LISTER_FILMS);
    }

    public void ajouterFilms(final ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.GERER_FILMS, new GererFilmsController(null));
    }
}
