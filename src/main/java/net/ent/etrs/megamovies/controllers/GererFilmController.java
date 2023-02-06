package net.ent.etrs.megamovies.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.references.Genre;
import net.ent.etrs.megamovies.view.converters.RealisateurConverter;

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
    public ComboBox<Genre> cbxGenre;
    @FXML
    public ComboBox<Realisateur> cbxRealisateur;
    @FXML
    public Button btnValider;
    private Film filmAModifier;

    private boolean modification;

    public GererFilmController(Film film) {
        this.filmAModifier = film;
        this.modification = Objects.nonNull(this.filmAModifier);
    }

    @FXML
    public void initialize() {
        initialiserFilm();
    }

    private void initGenre() {
        this.olstGenre.addAll(Arrays.asList(Genre.values()));
        this.cbxGenre.setItems(this.olstGenre);

    }

    private void initAuteur() {
        try {
            this.olstRealisateur.addAll(super.facadeMetierRealisateur.readAll());
            this.cbxRealisateur.setConverter(new RealisateurConverter());
            this.cbxRealisateur.setItems(this.olstRealisateur);
        } catch (BusinessException e) {
            e.printStackTrace();
        }


    }

    private void initialiserFilm() {

        if (this.modification) {
            this.tfTitre.setText(this.filmAModifier.getTitre());
            this.cbxGenre.setValue(this.filmAModifier.getGenre());
            this.cbxRealisateur.setValue(this.filmAModifier.getRealisateur());

            this.btnValider.setText("Modifier");
        }
    }

}
