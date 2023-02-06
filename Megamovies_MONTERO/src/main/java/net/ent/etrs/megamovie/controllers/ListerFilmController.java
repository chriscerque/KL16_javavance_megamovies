package net.ent.etrs.megamovie.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.entities.references.Genre;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovie.view.converter.GenreConverter;
import net.ent.etrs.megamovie.view.converter.LocalDateJfxConverter;
import net.ent.etrs.megamovie.view.references.ConstantesView;
import net.ent.etrs.megamovie.view.references.Screens;
import net.ent.etrs.megamovie.view.utils.AlerteUtils;
import net.ent.etrs.megamovie.view.utils.FxmlUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDate;
import java.util.Objects;

public class ListerFilmController extends AbstractController {

    private static Log logFile = LogFactory.getLog(Constantes.LOG_FILENAME);
    @FXML
    public TableView<Film> tvFilm;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDate;
    @FXML
    public TableColumn<Film, Genre> tcGenre;
    @FXML
    public TextField tfRecherche;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    private ObservableList<Film> oLstFILMS = FXCollections.observableArrayList();
    @FXML
    private FilteredList<Film> filteredListFILM = new FilteredList<>(this.oLstFILMS);

    @FXML
    public void initialize() {
        this.initTvFilm();
        this.initTfRecherche();
        this.cbGenre.setConverter(new GenreConverter());
        this.cbGenre.setPromptText(ConstantesView.SELECT_GENRE_MSG);
        this.cbGenre.getItems().addAll(Genre.values());

    }

    @FXML
    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            //filtrer les films par Titre
            this.filteredListFILM.setPredicate(film -> predicatFiltreFilm(newValue, film));
        });
        this.cbGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                //filtrer les films par Genre
                this.filteredListFILM.setPredicate(film -> predicatFiltreFilm(newValue.name(), film));
                ;
            }
        });

    }

    @FXML
    private boolean predicatFiltreFilm(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getTitre().toUpperCase().contains(newValue.toUpperCase()) || film.getGenre().name().toUpperCase().contains(newValue.toUpperCase())) {
            return true;
        }
        return false;
    }

    @FXML
    private void initTvFilm() {
        this.chargerListeFilms();
        this.tvFilm.setItems(this.filteredListFILM);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));
        this.tcGenre.setCellFactory(d -> new TextFieldTableCell<>(new GenreConverter()));

        this.tcDate.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcDate.setCellFactory(d -> new TextFieldTableCell<>(new LocalDateJfxConverter()));

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
        //Création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        //Création des sous menus contextuels
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
//        MenuItem itemListing = new MenuItem("Liste Des Films");
        MenuItem itemFilmographie = new MenuItem("Filmographie");
        //mappage des méthodes avec les sous menus contextuels
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        itemFilmographie.setOnAction(c -> filmographie());
//        itemFilmographie.setOnAction(c -> itemListing());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemFilmographie);
//        contextMenu.getItems().add(itemListing);
        return contextMenu;
    }
//
//    private void itemListing() {
//        FxmlUtils.chargerScene(Screens.LISTER_FILM_PAR_REA);
//    }

    private void filmographie() {
        // SI BDD implemente des ID des videos
//        FxmlUtils.chargerScene(Screens.FILMOGRAPHIE, new FilmographieController(this.tvFilm.getSelectionModel().getSelectedItem().getIdVideo()));
        // SINON GENERATION dune video random
        FxmlUtils.chargerScene(Screens.FILMOGRAPHIE, new FilmographieController(null));
    }


    @FXML
    public void modifier() {
        Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();

//        if (filmSelect != null) {

        FxmlUtils.chargerScene(Screens.FILM, new FilmController(filmSelect));


//        } else {
//            AlerteUtils.afficherMessageDansAlerte(ConstantesView.ERREUR_FILM_NON_SELECTIONNER, Alert.AlertType.NONE);
//        }

    }

    @FXML
    public void supprimer() {
//        System.out.println("supprimer");
        try {
            Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();
//            if (filmSelect != null) {

            super.FACADE_METIER_FILM.delete(filmSelect.getId());
            logFile.info("Film = " + filmSelect.getTitre() + " supprimer");
            this.chargerListeFilms();


//            } else {
//                AlerteUtils.afficherMessageDansAlerte(ConstantesView.ERREUR_FILM_NON_SELECTIONNER, Alert.AlertType.NONE);
//            }

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void chargerListeFilms() {
        this.oLstFILMS.clear();
        try {
            this.oLstFILMS.addAll(IterableUtils.toList(super.FACADE_METIER_FILM.selectAll()));
            logFile.info("Liste des film chargé");
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }
}
