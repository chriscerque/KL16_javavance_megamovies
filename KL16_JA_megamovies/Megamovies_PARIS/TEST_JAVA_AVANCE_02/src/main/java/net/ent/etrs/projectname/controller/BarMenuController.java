package net.ent.etrs.projectname.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.projectname.view.references.Screens;

import java.awt.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BarMenuController extends AbstractController{

    @FXML
    VBox laBarre;

    @FXML
    MenuBar menuBar;

    @FXML
    public void initialize(){
    }


    @FXML
    public void listerFilms(){
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_LISTER_FILMS, null);
    }

    @FXML
    public void ajouterFilm(){
        chargerScene(this.laBarre.getScene(), Screens.SCREEN_AJOUTER_FILM, null);
    }

    @FXML
    public void aide(){

    }

    @FXML
    public void quitter(){
        super.quitter();
    }
}
