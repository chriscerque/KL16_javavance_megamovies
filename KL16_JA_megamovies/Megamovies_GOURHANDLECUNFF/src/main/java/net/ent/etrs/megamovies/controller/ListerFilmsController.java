package net.ent.etrs.megamovies.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.controller.references.ConstantesMetier;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.exceptions.ViewException;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

import javax.persistence.RollbackException;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListerFilmsController extends AbstractController {
    final ObservableList<Film> oLstClients = FXCollections.observableArrayList();
    @FXML
    VBox barreMenu;
    @FXML
    TableView<Film> tvFilms;
    @FXML
    TableColumn<Film, String> tcTitre;
    @FXML
    TableColumn<Film, String> tcDateSortie;
    @FXML
    TableColumn<Film, String> tcGenre;
    @FXML
    TableColumn<Film, String> tcRealisateur;
    @FXML
    TextField tfFiltreFilm;
    @FXML
    ChoiceBox<Genre> cbGenre;
    @FXML
    Button btnCreer;
    @FXML
    Button btnModifier;
    @FXML
    Button btnSupprimer;
    FilteredList<Film> filteredLstFilms = new FilteredList<>(oLstClients, s -> true);
    SortedList<Film> sortedLstFilms = new SortedList<>(filteredLstFilms);

    @FXML
    public void initialize() {
        rechargerListeFilms();

        initFilteredLstFilms();

        chargerTvFilms();
    }

    private void rechargerListeFilms() {

        try {
            this.oLstClients.clear();
            this.oLstClients.addAll(FACADE_FILM.findAll().stream().sorted(this::comparerFilms)
                    .collect(Collectors.toList()));
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void initFilteredLstFilms() {
        this.filteredLstFilms = new FilteredList<>(oLstClients, s -> true);
        tfFiltreFilm.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstFilms.setPredicate(contact -> {
                boolean filtre = predicatFiltreLstFilms(newValue, contact);
                return filtre;
            });
        });
    }

    private boolean predicatFiltreLstFilms(String newValue, Film film) {
        // si le texte de filtre est vide ou null, on renvoit toute la liste.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        // on compare le titre avec le texte de filtre
        return film.getTitre().toLowerCase().contains(lowerCaseFilter);
    }

    private int comparerFilms(Film c1, Film c2) {
        int retour;
        retour = c1.getTitre().toLowerCase().compareTo(c2.getTitre().toLowerCase());
        if (retour == 0) {
            retour = c1.getTitre().toLowerCase().compareTo(c2.getTitre().toLowerCase());
        }
        return retour;
    }

    private void chargerTvFilms() {
        // Construction des cellules de chacune des colonnes
        tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tcDateSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        tcGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tcRealisateur.setCellValueFactory(new PropertyValueFactory<>("realisateur"));

        this.sortedLstFilms = new SortedList<>(this.filteredLstFilms);
        this.sortedLstFilms.comparatorProperty().bind(this.tvFilms.comparatorProperty());
        this.tvFilms.setItems(this.sortedLstFilms);

    }

    @FXML
    public void ajouterFilm() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_AJOUTER_FILM, new AjouterFilmController(null));
    }

    @FXML
    public void modifierFilm() {
        try {
            Film film = controlerFilmSelection();
            this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_AJOUTER_FILM,
                    new AjouterFilmController(film));
        } catch (ViewException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void supprimerFilm() {
        try {

            Film c = this.controlerFilmSelection();
            FACADE_FILM.delete(c);
            rechargerListeFilms();
        } catch (ViewException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        } catch (RollbackException | BusinessException e) {
            if (e.getMessage().contains("Error Code: 1451")) {
                AlerteUtils.afficherMessageDansAlerte(ConstantesMetier.MSG_SUPPRESSION_IMPOSSIBLE, Alert.AlertType.WARNING);
            }
        }
    }

    private Film controlerFilmSelection() throws ViewException {
        Film film = this.tvFilms.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(film)) {
            return film;
        } else {
            throw new ViewException(ConstantesMetier.MSG_FILM_NON_SELECTIONNE);
        }
    }
}
