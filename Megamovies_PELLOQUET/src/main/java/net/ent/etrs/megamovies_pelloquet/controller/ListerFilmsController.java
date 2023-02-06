package net.ent.etrs.megamovies_pelloquet.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.ent.etrs.megamovies_pelloquet.model.entities.Film;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;
import net.ent.etrs.megamovies_pelloquet.model.facade.Exceptions.BusinessException;
import net.ent.etrs.megamovies_pelloquet.view.utils.AlerteUtils;
import net.ent.etrs.megamovies_pelloquet.view.utils.Screens;

import java.time.LocalDate;
import java.util.Objects;

public class ListerFilmsController extends AbstractController{
    @FXML
    AnchorPane LaBase;
    @FXML
    TextField tfTitre;

    @FXML
    ComboBox cbbGenre;

    @FXML
    TableView<Film> tvFilm;
    @FXML
    TableColumn<Film, String> tcTitre;
    @FXML
    TableColumn<Film, LocalDate> tcDate;
    @FXML
    TableColumn<Film, Genre> tcGenre;

    ObservableList<Film> oLstFilm = FXCollections.observableArrayList();

    FilteredList<Film> fLstFilm = new FilteredList<>(oLstFilm, c -> true);


    @FXML
    public void initialize() {
        initOLstFilm();
        initFLstFilm();
    }

    private void initFLstFilm() {
        tfTitre.textProperty().addListener((observable, oldValue, newValue) -> {
            fLstFilm.setPredicate(Film ->{
                return PredicateFiltreFilmsTitre(newValue, Film);
            });
        });
    }

    private boolean PredicateFiltreFilmsTitre(String newValue, Film film) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return film.getTitre().toUpperCase().startsWith(newValue);
    }

    private void initOLstFilm() {
        try {
            this.oLstFilm.addAll(FACADE_FILM.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte("Impossible de trouver la liste de Films", Alert.AlertType.WARNING);
        }
    }

    private void chargerTvFilm() {
        this.tcTitre.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTitre()));
        this.tcDate.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDateSortie()));
        this.tcGenre.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getGenre()));

        this.tvFilm.setItems(this.fLstFilm);
        tvFilm.setRowFactory(r -> {
            return creationRow(r);
        });
    }

    private TableRow<Film> creationRow(TableView<Film> r) {
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

        // création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        // création du/des options du menu contextuel
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        // mappage de la méthode supprimer() sur le menuItem
        itemModifier.setOnAction(c -> modifierFilm());
        itemSupprimer.setOnAction(c -> supprimerFilm());
        // ajout du menuItem au contextMenu
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);

        return contextMenu;
    }

    private void supprimerFilm() {
        try {
            FACADE_FILM.delete(tvFilm.getSelectionModel().getSelectedItem());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void modifierFilm() {
        this.chargerScene(this.LaBase.getScene(), Screens.SCREEN_AJOUTER_FILM, null);
    }


}
