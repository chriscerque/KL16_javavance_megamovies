package net.ent.etrs.megamovies_pelloquet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies_pelloquet.view.utils.Screens;

public class BarreMenuController extends AbstractController{

    @FXML
    VBox laBarre;

    @FXML
    MenuBar menuBar;

    @FXML
    public void initialize() {
    }

    @FXML
    public void AjouterFilm() {
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_AJOUTER_FILM, null);
    }


    @FXML
    public void listerCommandesClient() {
        //chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_COMMANDES, new ListerCommandesClientController(null));
    }

    @FXML
    public void quitter() {
        super.quitter();
    }


    @FXML
    public void apropos() {
        super.aPropos();
    }

    public void ListerFilms(ActionEvent actionEvent) {
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_FILMS, null);
    }
}
