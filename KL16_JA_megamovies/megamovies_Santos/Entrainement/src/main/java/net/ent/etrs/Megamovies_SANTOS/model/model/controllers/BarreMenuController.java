package net.ent.etrs.Megamovies_SANTOS.model.model.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.Screens;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.utils.ClickableMenu;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.utils.FxmlUtils;



@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarreMenuController extends AbstractController {

    @FXML
    MenuBar menu;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.accueil());
        this.menu.getMenus().add(0, clickableMenu);
    }

    @FXML
    public void accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }





    public void gererFilm(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.GERER_FILM);
    }

    public void listerFilm(ActionEvent actionEvent) {
        FxmlUtils.chargerScene(Screens.LISTER_FILM);

    }

    public void quitter(ActionEvent actionEvent) {
        Platform.exit();
    }
}
