package net.ent.etrs.megamovies.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import lombok.SneakyThrows;
import net.ent.etrs.megamovies.model.daos.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;

import java.time.LocalDate;

public class ListerFilmsController extends AbstractController{

    public TextField tfRecherche;
    public ComboBox cbFilm;
    public TableView<Film> tvFilm;
    public TableColumn<Film, String> tcTitre;
    public TableColumn<Film, LocalDate> tcDate;
    public TableColumn<Film, Genre> tcGenre;

    private ObservableList<Film> oLstFilm = FXCollections.observableArrayList();

    private FilteredList<Film> filteredListFilm = new FilteredList<>(this.oLstFilm);

    public void initialize(){
        this.inittfRecherche();
        this.initTVFilm();
    }

    private void initTVFilm() {
        this.chargerListeFilm();
        this.tvFilm.setItems(this.filteredListFilm);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDate.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
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
        //Création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        //Création des sous menus contextuels
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        //mappage des méthodes avec les sous menus contextuels
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        return contextMenu;
    }

    private void supprimer() {
        System.out.println("supprimer");
        try {
            Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();
            if (filmSelect != null) {

                super.FACADE_FILM.delete(filmSelect);
                this.chargerListeFilm();


            } else {
                AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un film", Alert.AlertType.NONE);
            }

        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void modifier() {
        Film filmSelect = this.tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILMS, new GererFilmController());

    }


    @SneakyThrows
    private void chargerListeFilm() {
        this.oLstFilm.clear();
        this.oLstFilm.addAll(super.FACADE_FILM.findAll());

    }

    private void inittfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            //filtrer les film
            this.filteredListFilm.setPredicate(film -> predicatFiltreFilm(newValue, film));
        });
    }

    private boolean predicatFiltreFilm(final String newValue, final Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getTitre().toUpperCase().contains(newValue.toUpperCase())) {
            return true;
        }
        return false;
    }
}
