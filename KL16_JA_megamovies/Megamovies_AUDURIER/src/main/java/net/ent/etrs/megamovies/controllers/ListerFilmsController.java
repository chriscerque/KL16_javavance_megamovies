package net.ent.etrs.megamovies.controllers;

/*
    Project Name : TpGarage
    File created by : adrien.audurier
    Date of creation : 20/01/2023
*/

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.BusinessException;
import net.ent.etrs.megamovies.view.references.Screens;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.FxmlUtils;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ListerFilmsController extends AbstractController {

    private final ObservableList<Film> oLstFilms = FXCollections.observableArrayList();
    private final FilteredList<Film> fLstFilms = new FilteredList<>(oLstFilms);
    private final FilteredList<Film> fLstFilmsGenre = new FilteredList<>(fLstFilms);
    @FXML
    public TableView<Film> tvFilms;
    @FXML
    public TextField tfRecherche;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    public TableColumn<Film, String> tcGenre;


    public ListerFilmsController() {
    }

    public void initialize() {
        this.initTvFilms();
        this.initTfRecherche();
        this.initCbGenre();
    }

    private void initCbGenre() {
        this.cbGenre.getItems().addAll(Genre.values());
        this.cbGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.fLstFilmsGenre.setPredicate(new Predicate<Film>() {
                @Override
                public boolean test(final Film film) {
                    if (cbGenre.getValue() == null) {
                        return true;
                    }
                    return film.getGenre().equals(cbGenre.getValue());
                }
            });
        });
    }

    private void initTfRecherche() {
        this.tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            //filtrer les Films
            this.fLstFilms.setPredicate(new Predicate<Film>() {
                @Override
                public boolean test(final Film film) {
                    if (newValue.isBlank()) {
                        return true;
                    }
                    return film.getTitre().contains(newValue);
                }
            });
        });
    }


    private void initTvFilms() {
        this.chargerListeFilms();
        this.tvFilms.setItems(this.fLstFilmsGenre);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGenre().toString()));

        this.tvFilms.setRowFactory(this::creerRow);
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
        MenuItem itemEditer = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        MenuItem itemBandeAnnonce = new MenuItem("Visualiser la bande annonce");
        MenuItem itemFilmo = new MenuItem("Filmographie du réalisateur");
        //mappage des méthodes avec les sous menus contextuels
        itemEditer.setOnAction(c -> editer());
        itemSupprimer.setOnAction(c -> supprimer());
        itemBandeAnnonce.setOnAction(c -> bandeAnnonce());
        itemFilmo.setOnAction(c -> filmo());
        //ajout des menuItem dans le menu contextuel
        contextMenu.getItems().add(itemEditer);
        contextMenu.getItems().add(itemSupprimer);
        contextMenu.getItems().add(itemBandeAnnonce);
        contextMenu.getItems().add(itemFilmo);
        return contextMenu;
    }

    private void bandeAnnonce() {
    }

    private void filmo() {
        Film f = this.tvFilms.getSelectionModel().getSelectedItem();
        if (f != null) {
            FxmlUtils.chargerScene(Screens.FILMO, new VoirFilmoController(f.getRealisateur()));
        } else {
            AlerteUtils.afficherMessageDansAlerte("aucun réalisateur selctionné", Alert.AlertType.NONE);
        }
    }

    private void editer() {
        Film film = this.tvFilms.getSelectionModel().getSelectedItem();
        if (film != null) {
            FxmlUtils.openSubScreenAndWait("Modifier Film", Screens.GERER_FILMS, new GererFilmsController(film));
            this.chargerListeFilms();
        } else {
            AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un Film", Alert.AlertType.NONE);
        }
    }

    @FXML
    public void supprimer() {
        Film filmSelec = this.tvFilms.getSelectionModel().getSelectedItem();
        if (filmSelec != null) {
            try {
                FACADE_METIER_FILM.delete(filmSelec.getId());
            } catch (BusinessException e) {
                AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
            }
            this.chargerListeFilms();
        } else {
            AlerteUtils.afficherMessageDansAlerte("Veuillez sélectionner un militaire", Alert.AlertType.NONE);
        }
    }

    private void chargerListeFilms() {
        try {
            this.oLstFilms.clear();
            this.oLstFilms.addAll(IterableUtils.toList(FACADE_METIER_FILM.selectAll()));
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
        }
    }

//    public void click(final MouseEvent mouseEvent) {
//        if (mouseEvent.getClickCount() == 2) {
//            FxmlUtils.chargerScene(Screens.COMMANDES, new CommandesController(this.tvFilms.getSelectionModel().getSelectedItem()));
//        }
//    }
}
