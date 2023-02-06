package net.ent.etrs.megamovies.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.references.Genre;

public class ListerMoviesController extends AbstractController {

    private final ObservableList<Film> oLstFilms = FXCollections.observableArrayList();
    private final ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    @FXML
    TextField tfRecherche;
    @FXML
    ComboBox<Genre> cbxGenre;
    @FXML
    TableView tvFilms;
    @FXML
    TableColumn tcTitre;
    @FXML
    TableColumn tcDateSortie;
    @FXML
    TableColumn tcGenre;
    private FilteredList<Film> filteredLstFilms = new FilteredList<>(oLstFilms);
    private FilteredList<Genre> filteredLstGenre = new FilteredList<>(oLstGenre);

    public void initialize() {
        initCbxGenre();
        initTfRecherche();
    }

    private void initTfRecherche() {
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstFilms.setPredicate(film -> {
                boolean filtre = predicatFiltreLstFilms(newValue, film);
                return filtre;
            });
        });
    }

    private boolean predicatFiltreLstFilms(String newValue, Film film) {
        if ((newValue == null || newValue.isEmpty()) && film.getGenre().equals(this.cbxGenre.getSelectionModel().getSelectedItem())) {
            return true;
        }

        String upperCaseFilter = newValue.toUpperCase();

        return film.getTitre().toLowerCase().contains(upperCaseFilter) && film.getGenre().equals(this.cbxGenre.getSelectionModel().getSelectedItem());
    }

    private void initCbxGenre() {
        this.oLstGenre.addAll(Genre.values());
        this.cbxGenre.setItems(this.oLstGenre);

        this.cbxGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredLstFilms.setPredicate((film) -> predicatFiltreLstProduits(newValue, film));
        });

    }

    private Boolean predicatFiltreLstProduits(Genre newValue, Film film) {
        if (newValue == null) {
            return true;
        }
        return film.getGenre().equals(newValue);
    }
}
