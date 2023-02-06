package net.ent.etrs.tmplatejvsfx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.tmplatejvsfx.commons.validator.ValidException;
import net.ent.etrs.tmplatejvsfx.commons.validator.ValidatorUtils;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.entities.EntitiesFactory;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;
import net.ent.etrs.tmplatejvsfx.view.converter.RealisateurConverteur;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GererFilmController extends AbstractController{

    @FXML
    private TextField tfTitre;
    @FXML
    private DatePicker dpDateSortie;
    @FXML
    private ComboBox<Genre> cbxGenre;
    @FXML
    private ComboBox<Realisateur> cbxRealisateur;
    @FXML
    private Button btValider;
    private Film filmmodif;
    @FXML
    private Label lblMessage;

    private ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();

    public GererFilmController(final Film filmmodif) {
        this.filmmodif = filmmodif;
    }

    @FXML
    public void initialize() {

        verifierModeModification();
        initCbGenre();
        initCbxRealisateur();
    }

    private void initComoponents() {
        this.tfTitre.clear();
        this.dpDateSortie.setValue(null);
        this.cbxGenre.setValue(null);
        this.cbxRealisateur.setValue(null);
    }

    private void verifierModeModification() {
        if (!Objects.isNull(this.filmmodif)) {
            this.tfTitre.setText(this.filmmodif.getTitre());
            this.dpDateSortie.setValue(this.filmmodif.getDateSortie());
            this.cbxGenre.setValue(this.filmmodif.getGenre());
            this.cbxRealisateur.setValue(this.filmmodif.getRealisateur());

            this.btValider.setText("Modifier");
        }
    }

    private void initCbxRealisateur() {
        this.cbxRealisateur.setConverter(new RealisateurConverteur());
    }

    private void initCbGenre() {
        this.oLstGenre.addAll(Genre.values());
        this.cbxGenre.setItems(oLstGenre);
    }

    @FXML
    public void btValider() {
        try {

            if (!verifierFormulaire()) {

                String titre = this.tfTitre.getText();
                LocalDate dateSortie = this.dpDateSortie.getValue();
                Genre genre = this.cbxGenre.getValue();
                Realisateur realisateur = this.cbxRealisateur.getValue();

                String action = "créé";

                if (this.filmmodif != null) {
                    this.filmmodif.setTitre(titre);
                    this.filmmodif.setDateSortie(dateSortie);
                    this.filmmodif.setGenre(genre);
                    this.filmmodif.setRealisateur(realisateur);
                    action = "modifié";
                } else {
                    this.filmmodif = EntitiesFactory.fabriquerFilm(dateSortie, genre, titre, realisateur);
                }

                System.out.println(this.filmmodif.toString());
                super.FACADE_FILM.save(filmmodif);
                ValidatorUtils.validate(this.filmmodif);

                this.lblMessage.setText(String.format("le film a bien été %s", action));

            }
        } catch (ValidException e) {
            this.lblMessage.setText(e.getMapViolationsSB());
        } catch (BusinessException e) {
            this.lblMessage.setText(e.getMessage());
        }

    }

    private boolean verifierFormulaire() {
        return this.tfTitre.getText().isBlank() || this.dpDateSortie.getValue() == null || this.cbxGenre.getValue()==null;
    }

    private void disabled() {
        this.dpDateSortie.setDisable(true);
        this.cbxGenre.setDisable(true);
        this.cbxRealisateur.setDisable(true);

    }

    private void listenerGenre() {
        this.dpDateSortie.chronologyProperty().addListener(new ChangeListener<Chronology>() {
            @Override
            public void changed(final ObservableValue<? extends Chronology> observableValue, final Chronology oldValue, final Chronology newValue) {
                if (!newValue.toString().isBlank()) {
                    cbxGenre.setDisable(false);
                } else {
                    cbxGenre.setDisable(true);
                }
            }
        });
    }
    private void listenerDate() {
        this.tfTitre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (!newValue.isBlank()) {
                    dpDateSortie.setDisable(false);
                } else {
                    dpDateSortie.setDisable(true);
                }
            }
        });
    }

    private void listenerRealisateur() {
        this.tfTitre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (!newValue.isBlank()) {
                    cbxRealisateur.setDisable(false);
                } else {
                    cbxRealisateur.setDisable(true);
                }
            }
        });
    }


}
