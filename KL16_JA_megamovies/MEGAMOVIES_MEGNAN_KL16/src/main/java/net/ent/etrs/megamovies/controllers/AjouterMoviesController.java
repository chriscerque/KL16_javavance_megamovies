package net.ent.etrs.megamovies.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.model.entity.EntityFactory;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.entity.references.Genre;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.time.LocalDate;

public class AjouterMoviesController extends AbstractController {

    private final ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    private final ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();
    @FXML
    TextField tfTitre;
    @FXML
    ComboBox cbxGenre;
    @FXML
    ComboBox cbxRealisateur;
    @FXML
    DatePicker dpDateSortie;
    private Film filmCreer;

    public void initialize() {
        this.initCbxRealisateur();
        this.initCbxGenre();
    }

    public void creer() {
        try {

            if (!verifierFormulaire()) {
                filmCreer.setTitre(this.tfTitre.getText());
                filmCreer.setDateSortie(this.dpDateSortie.getValue());
                filmCreer.setGenre(Genre.valueOf(this.cbxGenre.getSelectionModel().toString()));
                filmCreer.setRealisateur((Realisateur) this.cbxRealisateur.getSelectionModel().getSelectedItem());
                filmCreer = EntityFactory.fabriquerFilm(filmCreer.getDateSortie(), filmCreer.getTitre(), filmCreer.getGenre(), filmCreer.getRealisateur());
            }
            AlerteUtils.afficherMessageDansAlerte("Le Film a bien été créé", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.WARNING);
        }
    }

    private boolean verifierFormulaire() {
        return this.tfTitre.getText().isBlank() || this.dpDateSortie.getValue().isBefore(LocalDate.now()) || this.cbxGenre.getSelectionModel().isEmpty() || this.cbxRealisateur.getSelectionModel().isEmpty();
    }

    private void initCbxGenre() {
        this.oLstGenre.addAll(Genre.values());
        this.cbxGenre.setItems(oLstGenre);
    }

    private void initCbxRealisateur() {
        try {
            this.oLstRealisateur.addAll(FACADE_REALISATEUR.readAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.WARNING);
        }
    }
}
