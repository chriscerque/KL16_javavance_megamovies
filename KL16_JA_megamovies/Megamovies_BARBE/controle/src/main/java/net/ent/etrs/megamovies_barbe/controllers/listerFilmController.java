package net.ent.etrs.megamovies_barbe.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.ent.etrs.megamovies_barbe.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_barbe.view.references.Screens;

import java.util.Objects;

public class listerFilmController extends AbstractController {


    @FXML
    Parent menu;

    @FXML
    TableView<Film> tvListerFilms;

    @FXML
    TableColumn<Film, String> tcTitre;

    @FXML
    TableColumn<Film, String> tcDateDeSortie;

    @FXML
    TableColumn<Film, String> tcGenre;

    @FXML
    TextField tfRecherche;

    @FXML
    ComboBox<Genre> comboBox;

    ObservableList<Film> olstFilm = FXCollections.observableArrayList();
    ObservableList<Genre> olstGenre = FXCollections.observableArrayList();
    FilteredList<Genre> filteredLstGenre = new FilteredList<>(olstGenre, c -> true);

    FilteredList<Film> filteredLstFilm = new FilteredList<>(olstFilm, c -> true);

    SortedList<Film> sortedLstFilm = new SortedList<>(filteredLstFilm,
            (c1, c2) -> c1.getTitre().compareTo(c2.getTitre()));


    @FXML
    public void initialize() {

        initFilteredList();
        chargerTvFilm();
        initCbxGenre();

    }

    private void initCbxGenre() {
        this.olstGenre.addAll(Genre.values());
        this.comboBox.setItems(this.olstGenre);

        this.comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredLstGenre.setPredicate((produit) -> predicatFiltreLstGenre(String.valueOf(newValue), produit));
        });

    }

    private void initFilteredList() {
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstFilm.setPredicate(film -> {
                return predicatFiltreLstFilm(newValue, film);
            });
        });

    }

    private boolean predicatFiltreLstFilm(String newValue, Film film) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return film.getTitre().toUpperCase().startsWith(newValue.toUpperCase());
    }

    private boolean predicatFiltreLstGenre(String newValue, Genre genre) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return String.valueOf(genre).toUpperCase().startsWith(newValue.toUpperCase());
    }

    private void chargerTvFilm() {
        chargerLstFilm();

        tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tcDateDeSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));

        this.sortedLstFilm.comparatorProperty().bind(this.tvListerFilms.comparatorProperty());
        this.tvListerFilms.setItems(sortedLstFilm);

        tvListerFilms.setRowFactory(r -> {
            return creationRow(r);
        });
    }

    private void chargerLstFilm() {

        try {
            this.olstFilm.clear();
            this.olstFilm.addAll(FACADE_METIER_FILM.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private TableRow<Film> creationRow(TableView<Film> r) {
        TableRow<Film> row = new TableRow<>();

        row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
            if (isEmpty) {
                row.setContextMenu(null);
            } else {
                // faire quelque chose avec le clique droit
                row.setContextMenu(creationMenuContextuel());
            }
        });
        return row;
    }

    private ContextMenu creationMenuContextuel() {

        // création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        // création du/des options du menu contextuel
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");

        // mappage de la méthode supprimer() sur le menuItem
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());

        // ajout du menuItem au contextMenu
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);

        return contextMenu;

    }

    @FXML
    public void modifier() {
        chargerScene(this.menu.getScene(), Screens.AJOUTER_FILMS, new ajouterFilmController(this.tvListerFilms.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void supprimer() {
        try {
            FACADE_METIER_FILM.delete(this.tvListerFilms.getSelectionModel().getSelectedItem());
            this.chargerLstFilm();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }


    }
}
