package net.ent.etrs.projectname.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.reference.Genre;
import net.ent.etrs.projectname.model.exceptions.BusinessException;
import net.ent.etrs.projectname.view.references.Screens;
import net.ent.etrs.projectname.view.utils.AlerteUtils;
import net.ent.etrs.projectname.view.utils.FxmlUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;

public class ListerFilmController extends AbstractController{

    @FXML
    TextField tfRechercheTitre;

    @FXML
    ComboBox cbxGenre;

    @FXML
    TableView<Film> tvFilms;

    @FXML
    TableColumn<Film, String> tcTitre;

    @FXML
    TableColumn<Film, LocalDate> tcDateSortie;

    @FXML
    TableColumn<Film, Genre> tcGenre;

    ObservableList<Film> olstFilms = FXCollections.observableArrayList();

    FilteredList<Film> filteredLstFilms = new FilteredList<>(olstFilms, f -> true);

    SortedList<Film> sortedLstFilms = new SortedList<>(filteredLstFilms,
            (f1, f2) -> f1.getTitre().compareTo(f2.getTitre()));

    public ListerFilmController(){

    }

    @FXML
    public void initialize(){
        initFilteredList();
        chargerTvFilms();
    }

    private void chargerTvFilms() {
        chargerLstFilms();

        tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tcDateSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        this.sortedLstFilms.comparatorProperty().bind(this.tvFilms.comparatorProperty());
        this.tvFilms.setItems(sortedLstFilms);

        tvFilms.setRowFactory(f -> {
            return creationRow(f);
        });
    }

    private TableRow<Film> creationRow(TableView<Film> f) {
        TableRow<Film> row = new TableRow<>();

        row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
            if (isEmpty) {
                row.setContextMenu(null);
            } else {
                row.setContextMenu(creationMenuContextuel());
            }
        });
        return row;
    }

    private ContextMenu creationMenuContextuel() {

        return null;
    }

    private void chargerLstFilms() {
        try {
            this.olstFilms.clear();
            this.olstFilms.addAll(FACADE_FILM.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void initFilteredList() {
        tfRechercheTitre.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstFilms.setPredicate(film -> {
                return predicatFiltreLstClients(newValue, film);
            });
        });
    }

    private boolean predicatFiltreLstClients(String newValue, Film film){
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return film.getTitre().toUpperCase().startsWith(newValue.toUpperCase());
    }

    @FXML
    public void supprimer(){
        Film filmSupprimer = tvFilms.getSelectionModel().getSelectedItem();
        olstFilms.remove(filmSupprimer);
    }

    @FXML
    public void modifier(){
        Film editFilm = tvFilms.getSelectionModel().getSelectedItem();
        FxmlUtils.chargerScene(tvFilms.getScene(), Screens.SCREEN_AJOUTER_FILM, new GererFilmController(editFilm));
    }

}
