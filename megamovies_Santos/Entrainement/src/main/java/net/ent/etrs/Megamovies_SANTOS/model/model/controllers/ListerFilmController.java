package net.ent.etrs.Megamovies_SANTOS.model.model.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Film;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.Screens;
import net.ent.etrs.Megamovies_SANTOS.model.start.view.utils.FxmlUtils;


import java.time.LocalDate;
@NoArgsConstructor
public class ListerFilmController extends AbstractController {
    public TextField tfRecherche;

    public TableView<Film> tvFilm;
    public TableColumn<Film, String> tcNom;


    public TableColumn<Film, LocalDate>  tcDateSortie;
    public TableColumn<Film, Genre> cbGenre;



    private final ObservableList<Film> oLstFilm = FXCollections.observableArrayList();

    private final FilteredList<Film> filteredListFilm = new FilteredList<>(this.oLstFilm);



    public void initialize() throws BusinessException {
        this.initTvTitre();
        this.initTfRecherche();

    }



    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            //filtrer les militaires
            this.filteredListFilm.setPredicate(film -> predicatFiltreFilm(newValue, film));
        });

    }
    private boolean predicatFiltreFilm(String newValue, Film film) {
        if (newValue == null || newValue.isBlank()) {
            return true;
        }
        if (film.getTitre().toUpperCase().contains(newValue.toUpperCase()) ) {
            return true;
        }
        return false;
    }

    private void initTvTitre() throws BusinessException {
        this.chagerListeFilm();



        this.tcNom.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));

        this.cbGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));



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
        //mappage des méthodes avec les sous menus contextuels
        itemModifier.setOnAction(c -> modifier());
        itemSupprimer.setOnAction(c -> supprimer());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);
        return contextMenu;
    }

    @FXML
    public void modifier() {
        Film FilmSelect = this.tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.GERER_FILM, new GererFilmController(FilmSelect));

    }

    @FXML
    public void supprimer() {
        System.out.println("supprimer");
        try {
            Film filmselect = this.tvFilm.getSelectionModel().getSelectedItem();
            if (filmselect != null) {

               FACADE_FILM.delete(filmselect);
                this.chagerListeFilm();


            } else {
                net.ent.etrs.projet.model.view.utils.AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un Film", Alert.AlertType.NONE);
            }

        } catch (BusinessException e) {
            net.ent.etrs.projet.model.view.utils.AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void chagerListeFilm() throws BusinessException {
        this.oLstFilm.clear();
        this.oLstFilm.addAll(FACADE_FILM.findAll());
    }

}

