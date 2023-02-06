package net.ent.etrs.megamovies.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListerFilmographieController extends AbstractController{
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
    ChoiceBox<Realisateur> cbRealisateur;
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
        cbRealisateur.itemsProperty().addListener((observable, oldValue, newValue) -> {
            filteredLstFilms.setPredicate(contact -> {
                boolean filtre = predicatFiltreLstFilms(newValue.get(0), contact);
                return filtre;
            });
        });
    }

    private boolean predicatFiltreLstFilms(Realisateur newValue, Film film) {
        // si aucun choix de réalisateur est sélectionné, on renvoit toute la liste.
        if (newValue == null) {
            return true;
        }
        // on compare le réalisateur avec la choiceBox qui filtre
        return film.getRealisateur().equals(newValue);
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

        this.sortedLstFilms = new SortedList<>(this.filteredLstFilms);
        this.sortedLstFilms.comparatorProperty().bind(this.tvFilms.comparatorProperty());
        this.tvFilms.setItems(this.sortedLstFilms);
    }
}
