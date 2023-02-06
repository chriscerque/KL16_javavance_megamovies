package net.ent.etrs.megamovies.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.converter.LocalDateJfxConverter;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

import java.time.LocalDate;

public class ListerFilmController extends AbstractController {

    @FXML
    public TextField tfRecherche;
    @FXML
    public TableView<Film> tvFilm;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    public TableColumn<Film, Genre> tcGenre;
    public ComboBox<Genre> cbGenre;

    private ObservableList<Film> oLstFilms = FXCollections.observableArrayList();

    private FilteredList<Film> filteredListFilm = new FilteredList<>(this.oLstFilms);


    public void initialize() throws BusinessException {
        this.cbGenre.getItems().addAll(Genre.values());
        this.initTvFilm();
        this.initTfRecherche();
        this.initCbRecherch();
    }

    @FXML
    private void initTvFilm() throws BusinessException {
        this.chargerListeFilm();
        this.tvFilm.setItems(this.filteredListFilm);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().connaitreGenre()));
        this.tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcDateSortie.setCellFactory(d -> new TextFieldTableCell<>(new LocalDateJfxConverter()));

        this.tvFilm.setRowFactory(tv -> creerRow(tv));
    }

    @FXML
    private TableRow<Film> creerRow(TableView<Film> tv) {
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

    @FXML
    private ContextMenu creerContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        return contextMenu;
    }

    @FXML
    public void modifier() {
        Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.GERER_FILM, new GererFilmController(filmSelect));
    }

    @FXML
    public void supprimer() {
        System.out.println("supprimer");
        try {
            Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();
            if (filmSelect != null) {
                super.FACADE_METIER.delete(filmSelect.getId());
                this.chargerListeFilm();
            } else {
                AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un film", Alert.AlertType.NONE);
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void chargerListeFilm() throws BusinessException {
        this.oLstFilms.clear();
        this.oLstFilms.addAll(super.FACADE_METIER.findAll());
    }

    @FXML
    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {

            this.filteredListFilm.setPredicate(perso -> predicatFiltreFilm(newValue, perso));
        });
    }

    @FXML
    private void initCbRecherch() {
        this.cbGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            this.filteredListFilm.setPredicate(perso -> predicatFiltreFilmGenre(String.valueOf(newValue), perso));

        });
    }

    @FXML
    private boolean predicatFiltreFilmGenre(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getGenre().equals(Genre.valueOf(newValue))) {
            return true;
        }
        return false;
    }

    @FXML
    private boolean predicatFiltreFilm(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getTitre().toUpperCase().contains(newValue.toUpperCase())) {
            return true;
        }
        return false;
    }
}
