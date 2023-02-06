package net.ent.etrs.megamovies.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megamovies.commons.utils.jxf.AlerteUtils;
import net.ent.etrs.megamovies.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.references.Genre;
import net.ent.etrs.megamovies.view.references.Screens;

import java.time.LocalDate;
import java.util.Objects;

public class ListerFilmController extends AbstractController {


    private final ObservableList<Film> olstFilms = FXCollections.observableArrayList();
    private final ObservableList<Genre> olstGenres = FXCollections.observableArrayList();
    @FXML
    public TableView<Film> tvFilms;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    public TableColumn<Film, Genre> tcGenre;
    @FXML
    public TextField tfRechercheTitre;
    @FXML
    public ComboBox<Genre> cbxGenre;
    private FilteredList<Film> filteredListFilm = new FilteredList<>(olstFilms);

    @FXML
    public void initialize() {
        initOLstFilms();
        initOLstGenre();
        initRechercheTitre();

        chargerTvFilms();
    }

    private void initOLstFilms() {

        try {
            this.olstFilms.clear();
            this.olstFilms.addAll(super.facadeMetierFilm.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }


    private void initOLstGenre() {
        this.olstGenres.clear();
        this.olstGenres.addAll(Genre.values());
    }

    private void initRechercheTitre() {
        this.tfRechercheTitre.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredListFilm.setPredicate(film -> {

                return predicatFiltreLstFilms(newValue, film);
            });
        });
    }

    private boolean predicatFiltreLstFilms(String newValue, Film film) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return film.getTitre().toUpperCase().contains(newValue.toUpperCase());
    }


    private void chargerTvFilms() {
        this.tvFilms.setItems(this.filteredListFilm);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));

        this.tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));

        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));

//		this.tcDateSortie.setCellFactory(a -> new TextFieldTableCell<Film, LocalDate>(new LocalDateJFXConverter()));

        this.tvFilms.setRowFactory(r -> creationRow(r));

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
        MenuItem itemVisionner = new MenuItem("Visionner");
        // mappage de la méthode supprimer() sur le menuItem
        itemModifier.setOnAction(c -> modifierFilm());
        itemSupprimer.setOnAction(c -> supprimerFilm());
        itemVisionner.setOnAction(c -> visionner());
        // ajout du menuItem au contextMenu
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemVisionner);

        return contextMenu;

    }

    private void modifierFilm() {
        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILM, new GererFilmController(this.tvFilms.getSelectionModel().getSelectedItem()));
    }

    private void supprimerFilm() {
        try {
            super.facadeMetierFilm.delete(this.tvFilms.getSelectionModel().getSelectedItem());
            this.initOLstFilms();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void visionner() {
        //TODO
    }


}
