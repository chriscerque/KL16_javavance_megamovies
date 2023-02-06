package net.ent.etrs.megamovies.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.view.utils.ClickableMenu;
import net.ent.etrs.megamovies.view.utils.Screens;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarreMenuController extends AbstractController {

    @FXML
    VBox laBarre;

    @FXML
    MenuBar menuBar;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.accueil());
        this.menuBar.getMenus().add(0, clickableMenu);
    }

    @FXML
    public void listerFilms() {
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_FILMS, null);
    }

    @FXML
    public void gererFilms() {
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_GERER_FILMS,null);
    }



    @FXML
    public void accueil() {
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_ACCUEIL, null);
    }


    @FXML
    public void quitter() {
        super.quitter();
    }

    @FXML
    public void notifyClicked(Event event) {
        System.out.println(event);
        this.accueil();
    }

    @FXML
    public void apropos() {
        super.aPropos();
    }
}
