package net.ent.etrs.megamovies_barbe.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megamovies_barbe.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_barbe.commons.validator.ValidException;
import net.ent.etrs.megamovies_barbe.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies_barbe.model.entity.EntitiesFactory;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_barbe.view.references.ConstantesView;
import net.ent.etrs.megamovies_barbe.view.references.converter.LocalDateConverter;
import net.ent.etrs.megamovies_barbe.view.references.converter.RealisateurConverter;

import java.time.LocalDate;
import java.util.Objects;

public class ajouterFilmController extends AbstractController {

    @FXML
    TextField tfTitre;

    @FXML
    DatePicker dpDateDeSortie;

    @FXML
    ComboBox<Genre> cbxGenre;
    @FXML
    Button btnValider;

    @FXML
    ComboBox<Realisateur> cbxRealisateur;
    Realisateur realisateur;
    ObservableList<Genre> olstGenre = FXCollections.observableArrayList();
    FilteredList<Genre> filteredLstGenre = new FilteredList<>(olstGenre, c -> true);
    ObservableList<Realisateur> olstRealisateur = FXCollections.observableArrayList();
    FilteredList<Realisateur> filteredLstRealisateur = new FilteredList<>(olstRealisateur, c -> true);

    private Film film;

    public ajouterFilmController(Film film) {
        this.film = film;
    }

    public void initialize() {
        this.initComoponents();
        if (this.film != null) {
            this.tfTitre.setText(this.film.getTitre());
            this.dpDateDeSortie.setValue(this.film.getDateSortie());
            this.cbxRealisateur.setValue(this.film.getRealisateur());
            this.cbxGenre.setValue(this.film.getGenre());
            this.btnValider.setText("Modifier");
        }
    }

    private void initComoponents() {
        this.tfTitre.clear();
        this.gererDpDateSortie();
        this.initCbxGenre();
        this.initCbxRealisateur();
    }

    private void gererDpDateSortie() {
        this.dpDateDeSortie.setConverter(new LocalDateConverter());
        this.dpDateDeSortie.setPromptText(ConstantesView.PATTERN_FORMAT_DATE);
        
    }

    private void initCbxGenre() {
        this.olstGenre.addAll(Genre.values());
        this.cbxGenre.setItems(this.olstGenre);

        this.cbxGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredLstGenre.setPredicate((produit) -> predicatFiltreLstGenre(String.valueOf(newValue), produit));
        });

    }

    private boolean predicatFiltreLstGenre(String newValue, Genre genre) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return String.valueOf(genre).toUpperCase().startsWith(newValue.toUpperCase());
    }

    private void initCbxRealisateur() {

        this.cbxRealisateur.setItems(this.olstRealisateur);
        this.cbxRealisateur.setConverter(new RealisateurConverter());

    }

    private boolean predicatFiltreLstGenre(String newValue, Realisateur realisateur) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return String.valueOf(realisateur).toUpperCase().startsWith(newValue.toUpperCase());
    }

    public void valider(ActionEvent actionEvent) {

        if (!verifierFormulaire()) {
            try {
                String titre = this.tfTitre.getText();
                Realisateur realisateur = this.cbxRealisateur.getValue();
                Genre genre = this.cbxGenre.getValue();
                LocalDate date = this.dpDateDeSortie.getValue();

                this.film = EntitiesFactory.fabriquerFilm(date, genre, titre, realisateur);
                ValidatorUtils.validate(this.film);
                FACADE_METIER_FILM.save(film);
                AlerteUtils.afficherMessageDansAlerte("le film a bien été créé", Alert.AlertType.CONFIRMATION);
                this.initComoponents();
            } catch (BusinessException e) {
                AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.ERROR);
            } catch (ValidException e) {
                AlerteUtils.afficherMessageDansAlerte(e.getMapViolationsSB(), Alert.AlertType.ERROR);
            }
        }


    }

    private boolean verifierFormulaire() {
        return this.tfTitre.getText().isBlank() || this.cbxGenre.getPromptText().isBlank() ||
                this.cbxRealisateur.getPromptText().isBlank() || this.dpDateDeSortie.getPromptText().isBlank();
    }

}
