package net.ent.etrs.megamovies.controllers;

/*
    Project Name : TpGarage
    File created by : adrien.audurier
    Date of creation : 20/01/2023
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.commons.validator.ValidException;
import net.ent.etrs.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.BusinessException;
import net.ent.etrs.megamovies.view.references.callback.DatePickerCallBack;
import net.ent.etrs.megamovies.view.references.converter.RealisateurStringConverter;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.Objects;


public class GererFilmsController extends AbstractController {
    private final boolean modification;
    private final ObservableList<Genre> oListGenre = FXCollections.observableArrayList();
    private final ObservableList<Realisateur> oListReal = FXCollections.observableArrayList();
    @FXML
    public Button btnCreer;
    @FXML
    public TextField tfTitre;
    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbReal;
    private Film film;

    public GererFilmsController(final Film film) {
        this.film = film;
        this.modification = (this.film != null);
    }

    public void initialize() {
        this.initTfTitre();
        this.initCbGenre();
        this.initCbReal();
        this.initDpDateSortie();
        if (modification) {
            afficherValeurs();
        } else {
            this.disableFields();
        }
    }

    private void disableFields() {
        this.dpDateSortie.setDisable(true);
        this.cbGenre.setDisable(true);
        this.cbReal.setDisable(true);
    }

    private void initTfTitre() {
        this.tfTitre.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean val = newValue.isBlank();
            this.dpDateSortie.setDisable(val);
        });
    }

    private void initDpDateSortie() {
        this.dpDateSortie.setDayCellFactory(new DatePickerCallBack());
        this.dpDateSortie.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.cbGenre.setDisable(false);
            }
        });
    }

    private void initCbReal() {
        try {
            this.oListReal.addAll(IterableUtils.toList(FACADE_METIER_REALISATEUR.selectAll()));
            this.cbReal.setItems(oListReal);
            this.cbReal.setConverter(new RealisateurStringConverter());

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
        }
    }

    private void initCbGenre() {
        this.cbGenre.getItems().addAll(Genre.values());
        this.cbGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.cbReal.setDisable(false);
            }
        });
    }

    private void afficherValeurs() {
        this.tfTitre.setText(film.getTitre());
        this.dpDateSortie.setValue(film.getDateSortie());
        this.cbGenre.setValue(film.getGenre());
        this.cbReal.setValue(film.getRealisateur());
    }

    public void valider(final ActionEvent actionEvent) {
        String titre = this.tfTitre.getText();
        LocalDate date = this.dpDateSortie.getValue();
        Genre genre = this.cbGenre.getValue();
        Realisateur realisateur = this.cbReal.getValue();
        try {
            if (Objects.nonNull(this.film)) {
                this.film.setTitre(titre);
                this.film.setDateSortie(date);
                this.film.setGenre(genre);
                this.film.setRealisateur(realisateur);
            } else {
                this.film = EntitiesFactory.fabriquerFilm(titre, genre, date, realisateur);
            }
            ValidatorUtils.validate(this.film);
            FACADE_METIER_FILM.save(this.film);
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        } catch (ValidException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMapViolationsSB(), Alert.AlertType.WARNING);
            if (this.film != null) {
                this.refresh(this.film);
            }
        }
    }


    private void refresh(Film film) {
        try {
            FACADE_METIER_FILM.refresh(film);
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }
}
