package net.ent.etrs.templatefx.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.references.Genre;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;
import net.ent.etrs.templatefx.view.converter.DateConverter;
import net.ent.etrs.templatefx.view.references.Screens;
import net.ent.etrs.templatefx.view.utils.AlerteUtils;
import net.ent.etrs.templatefx.view.utils.FxmlUtils;

import java.time.LocalDate;

public class ListerFilmController extends AbstractController {
    @FXML
    public TextField tfRecherche;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public TableView<Film> tvFilm;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    public TableColumn<Film, Genre> tcGenre;

    private ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    private ObservableList<Film> oLstFilms = FXCollections.observableArrayList();

    private FilteredList<Film> filteredListFilm = new FilteredList<>(this.oLstFilms);

    public void initialize() {
        this.initTvFilm();
        this.initCbGenre();
        this.initTfRecherche();
    }

    private void initTvFilm() {
        this.chargerListeFilms();
        this.tvFilm.setItems(this.filteredListFilm);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcDateSortie.setCellFactory(tc -> new TextFieldTableCell<>(new DateConverter()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));

        this.tvFilm.setRowFactory(tv -> creerRow(tv));
    }

    private TableRow<Film> creerRow(final TableView<Film> tv) {
        TableRow<Film> row = new TableRow<>();

        row.emptyProperty().addListener((observable, wasEmpty, isEmpty) -> {
            if (isEmpty) {
                row.setContextMenu(null);
            } else {
                row.setContextMenu(creerContextMenu());
            }
        });
        return row;
    }

    private ContextMenu creerContextMenu() {

        ContextMenu contextMenu = new ContextMenu();

        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        MenuItem itemVisionner = new MenuItem("Visionner bande-annonce");
        MenuItem itemFilmo = new MenuItem("Voir les filmographies");

        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        itemVisionner.setOnAction(c -> visionner());
        itemFilmo.setOnAction(c -> voirFilmo());

        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemVisionner);
        contextMenu.getItems().add(itemFilmo);
        return contextMenu;
    }

    private void voirFilmo() {
        FxmlUtils.chargerScene(Screens.VOIR_FILMOGRAPHIE, new VoirFilmographieController(this.tvFilm.getSelectionModel().getSelectedItem().getRealisateur()));
    }

    private void visionner() {
        //TODO
    }

    private void supprimer() {
        try {
            Film film = this.tvFilm.getSelectionModel().getSelectedItem();
            if (film != null) {
                super.fmf.delete(film);
                this.chargerListeFilms();
            } else {
                AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un film", Alert.AlertType.NONE);
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void modifier() {
        Film film = this.tvFilm.getSelectionModel().getSelectedItem();
        if (film != null) {
            FxmlUtils.chargerScene(Screens.AJOUTER_FILM, new AjouterFilmController(film));
        } else {
            AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un film", Alert.AlertType.NONE);
        }
    }

    private void chargerListeFilms() {
        try {
            this.oLstFilms.clear();
            this.oLstFilms.addAll(super.fmf.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void initCbGenre() {
        this.oLstGenre.clear();
        this.oLstGenre.addAll(Genre.values());
        this.cbGenre.setItems(oLstGenre);
    }

    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredListFilm.setPredicate(film -> predicatFiltreFilm(newValue, film));
        });
    }

    private boolean predicatFiltreFilm(final String newValue, final Film film) {
        if (newValue == null || newValue.isBlank()) {
            if (!(this.cbGenre.getValue() == null)) {
                return film.getGenre().equals(this.cbGenre.getValue());
            }
            return true;
        } else {
            if (!(this.cbGenre.getValue() == null)) {
                return film.getGenre().equals(this.cbGenre.getValue()) &&
                        film.getTitre().toUpperCase().contains(newValue.toUpperCase());
            } else {
                return film.getTitre().toUpperCase().contains(newValue.toUpperCase());
            }
        }
    }
}
