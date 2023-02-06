package net.ent.etrs.megamovies.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.converter.RealisateurConverter;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;


import java.util.Arrays;
import java.util.Objects;

public class GererFilmController extends AbstractController {
    final ObservableList<Genre> olstGenre = FXCollections.observableArrayList();
    final ObservableList<Realisateur> olstRealisateur = FXCollections.observableArrayList();
    @FXML
    public TextField tfTitre;
    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    @FXML
    public Button btnValider;
    private Film filmModif;

    private boolean modification;

    public GererFilmController(Film film) {
        this.filmModif = film;
        this.modification = Objects.nonNull(this.filmModif);
    }

    @FXML
    public void initialize() {

        initCbGenre();
        initCbRealisateur();
        initialiserFilm();

    }

    private void initCbGenre() {

        this.olstGenre.addAll(Arrays.asList(Genre.values()));
        this.cbGenre.setItems(this.olstGenre);

    }

    private void initCbRealisateur() {
        try {
            this.olstRealisateur.addAll(super.facadeRealisateur.selectAll());
            this.cbRealisateur.setConverter(new RealisateurConverter());
            this.cbRealisateur.setItems(this.olstRealisateur);
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }

    private void initialiserFilm() {

        if (this.modification) {
            this.tfTitre.setText(this.filmModif.getTitre());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.cbRealisateur.setValue(this.filmModif.getRealisateur());
            this.btnValider.setText("Modifier");
        }
    }

    private void disabled() {
        this.btnValider.setDisable(true);
        this.tfTitre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (!(.getText().isBlank() || tfCourriel.getText().isBlank())) {
                    btnValider.setDisable(newValue.isBlank());
                }
            }
        });
        this.tfPrenom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (!(tfCourriel.getText().isBlank() || tfNom.getText().isBlank())) {
                    btnComfirmer.setDisable(newValue.isBlank());
                }
            }
        });
        this.tfCourriel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (!(tfNom.getText().isBlank() || tfPrenom.getText().isBlank())) {
                    btnComfirmer.setDisable(newValue.isBlank());
                }
            }
        });
    }

}
