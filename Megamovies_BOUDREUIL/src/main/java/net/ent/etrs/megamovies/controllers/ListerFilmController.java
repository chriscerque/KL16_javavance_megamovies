package net.ent.etrs.megamovies.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.converter.LocalDateJfxConverter;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;
import org.apache.commons.collections4.IteratorUtils;

import java.time.LocalDate;
import java.util.Optional;

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
    public TextField tfRecherche;
    @FXML
    public ComboBox<Genre> cbGenre;
    private FilteredList<Film> filteredListFilm = new FilteredList<>(olstFilms);

    @FXML
    public void initialize() {
        initOLstFilms();
        chargerCbgenre();
        initTfRechercheTitre();
        chargerTvFilms();
    }

    private void initOLstFilms() {

        try {
            this.olstFilms.clear();
            this.olstFilms.addAll(super.facadeFilm.selectAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void chargerCbgenre() {
        this.olstGenres.clear();
        this.olstGenres.addAll(Genre.values());
        this.cbGenre.setItems(this.olstGenres);
    }

    private void initTfRechercheTitre() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredListFilm.setPredicate(film -> predicatFiltreLstFilms(newValue, film));
        });
    }

    private boolean predicatFiltreLstFilms(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        return film.getTitre().toUpperCase().contains(newValue.toUpperCase());

    }

    private void chargerTvFilms() {

        this.chargerListeFilm();
        this.tvFilms.setItems(this.filteredListFilm);
        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));

        this.tvFilms.setRowFactory(r -> creerRow(r));

    }

    private void chargerListeFilm() {
        this.olstFilms.clear();
        try {
            this.olstFilms.addAll(IteratorUtils.toList(super.facadeFilm.selectAll().iterator()));
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private TableRow<Film> creerRow(TableView<Film> r) {
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
        //Création des sous menus contextuels
        MenuItem itemDetail = new MenuItem("Détails");
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        //mappage des méthodes avec les sous menus contextuels
        itemDetail.setOnAction(c -> detaillerFilm());
        itemModifier.setOnAction(c -> modifierFilm());
        itemSupprimer.setOnAction(c -> supprimerFilm());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemDetail);
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);

        return contextMenu;

    }
    @FXML
    public void detaillerFilm() {

        Boolean retour = false;
        LocalDateJfxConverter dtf = new LocalDateJfxConverter();
        Film film = this.tvFilms.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Détails pour : " + film.getTitre());
        a.setHeaderText("Détails pour : " + film.getTitre());
        a.setContentText("Genre : " + film.getGenre() + "\n " +
                "Realisateur : " + film.getRealisateur().getNom() + "\n " +
                "Date de sortie : " + dtf.toString(film.getDateSortie()));
        Optional<ButtonType> oBtnType = a.showAndWait();
    }
    private void modifierFilm() {
        FxmlUtils.chargerScene(Screens.GERER_FILM, new GererFilmController(this.tvFilms.getSelectionModel().getSelectedItem()));
    }

    private void supprimerFilm() {
        try {
            super.facadeFilm.delete(this.tvFilms.getSelectionModel().getSelectedItem());
            this.initOLstFilms();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }
}
