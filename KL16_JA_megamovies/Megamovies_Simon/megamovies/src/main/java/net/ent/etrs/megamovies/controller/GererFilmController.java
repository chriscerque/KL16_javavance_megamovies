package net.ent.etrs.megamovies.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;


public class GererFilmController extends AbstractController{

    @FXML
    public TextField tfTitre;
    @FXML
    public DatePicker dpDate;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    @FXML
    public Button btnValider;


    private ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    private ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();





    @FXML
    public void initialize(){
        chargercbGenre();
        gererSelectionnerGenre();
    }

    private void gererSelectionnerGenre() {
        this.cbGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> traiterSelectionGenre(oldValue, newValue));
    }

    private void traiterSelectionGenre(final Genre oldValue, final Genre newValue) {
        if (newValue != null) {
            this.cbGenre.getItems().remove(newValue);
            if (oldValue != null) {
                this.cbGenre.getItems().add(oldValue);
            }
            this.cbGenre.setDisable(false);
        } else {
            this.chargercbGenre();
            this.cbGenre.setDisable(true);
        }
    }

    private void chargercbGenre() {
        this.cbGenre.setItems(this.oLstGenre);
    }


}
