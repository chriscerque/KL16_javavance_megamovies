package net.ent.etrs.megamovies_falgat.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import net.ent.etrs.megamovies_falgat.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_falgat.commons.utils.FxmlUtils;
import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_falgat.view.converters.LocalDateJFXConverter;
import net.ent.etrs.megamovies_falgat.view.references.ConstanteView;
import net.ent.etrs.megamovies_falgat.view.references.Screens;

import java.time.LocalDate;

public class ListerFilmsController extends AbstractController{


    //TableView -> Liste des films

    @FXML
    private TableView<Film> tvFilm;

    @FXML
    private TableColumn<Film, String> tcTitre;

    @FXML
    private TableColumn<Film, LocalDate> tcDate;

    @FXML
    private TableColumn<Film, Genre> tcGenre;

    private ObservableList<Film> oLstFilms = FXCollections.observableArrayList();

    //Recherche et filtre par genre

    @FXML
    private TextField tfRecherche;

    @FXML
    private ComboBox<Genre> cbGenre;

    private ObservableList<Genre> oLstGenres = FXCollections.observableArrayList();

    private FilteredList<Film> filteredListFilms = new FilteredList<>(this.oLstFilms);


    public void initialize(){

        this.initOLstFilm();
        this.chargerTvFilms();

        this.initCbGenre();

        this.initTfRecherche();


    }

    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            //filtrer les films
            this.filteredListFilms.setPredicate(film -> predicatFiltreMilitaire(newValue, film));
        });

    }

    private boolean predicatFiltreMilitaire(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getTitre().toUpperCase().contains(newValue.toUpperCase())) {
            return true;
        }
        return false;
    }

    private void initCbGenre() {

        this.oLstGenres.addAll(Genre.values());

        this.cbGenre.setItems(oLstGenres);

        this.cbGenre.setPromptText(ConstanteView.PROMPT_SELECTIONNER);

    }


    public void initOLstFilm(){
        try {

            this.oLstFilms.clear();
            this.oLstFilms.addAll(super.facadeFilm.readAllFilms());

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void chargerTvFilms(){

        this.tvFilm.setItems(this.filteredListFilms);

        this.tcDate.setCellFactory(d -> new TextFieldTableCell<>(new LocalDateJFXConverter()));

        this.tcTitre.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDate.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcGenre.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getGenre()));

        this.tvFilm.setRowFactory(tv -> creerRow(tv));

    }

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

    private ContextMenu creerContextMenu() {
        //Création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        //Création des sous menus contextuels
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        MenuItem itemAnnonce = new MenuItem("Bande annonce");
        MenuItem itemFilmo = new MenuItem("Voir filmographies");
        //mappage des méthodes avec les sous menus contextuels
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        itemAnnonce.setOnAction(c -> annonce());
        itemFilmo.setOnAction(c -> filmo());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemAnnonce);
        contextMenu.getItems().add(itemFilmo);
        return contextMenu;
    }

    private void filmo() {

        FxmlUtils.chargerScene(Screens.SCREEN_FILMO, new FilmographieController());
    }

    public void triParGenre(){

        Genre genre = this.cbGenre.getSelectionModel().getSelectedItem();

        this.oLstFilms.clear();


        try {
            this.oLstFilms.addAll(super.facadeFilm.readAllFilmsByGenre(genre));
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void modifier(){

        Film film = this.tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.SCREEN_CREER_FILM, new CreerFilmController(film));

    }

    public void supprimer(){
        try {

            Film f = this.tvFilm.getSelectionModel().getSelectedItem();
            super.facadeFilm.delete(f.getId());

            this.oLstFilms.clear();

            this.oLstFilms.addAll(super.facadeFilm.readAllFilms());

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void annonce(){

        Film f = this.tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.SCREEN_ANNONCE, new BandeAnnonceController("https://youtu.be/XgVADKKb4jI"));

    }





}
