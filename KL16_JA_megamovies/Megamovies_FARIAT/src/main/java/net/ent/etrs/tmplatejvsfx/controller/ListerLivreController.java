package net.ent.etrs.tmplatejvsfx.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.ViewException;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;
import net.ent.etrs.tmplatejvsfx.view.ConstantesView;
import net.ent.etrs.tmplatejvsfx.view.references.Screens;
import net.ent.etrs.tmplatejvsfx.view.utils.AlerteUtils;
import net.ent.etrs.tmplatejvsfx.view.utils.FxmlUtils;

import java.time.LocalDate;
import java.util.Objects;

public class ListerLivreController extends AbstractController {

    @FXML
    private TableView<Film> tvFilm;
    @FXML
    private TextField tfRechercheTitre;

    @FXML
    private ComboBox<Genre> cbxGenreList;

    @FXML
    private TableColumn<Film, String> tcTitre;
    @FXML
    private TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    private TableColumn<Film, Genre>tcGenre;



    private ObservableList<Film> oLstFilm = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initOLstFilm();
        chargerTvFilm();
    }

    private void chargerTvFilm() {
        this.tvFilm.setItems(oLstFilm);
        // Avec les lambdas JAVA 8
        tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));

        tcDateSortie.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));


        tfRechercheTitre.setPromptText("veuillez écrire votre recherche");
    }

    private void initOLstFilm() {
        try {

            this.oLstFilm.clear();
            this.oLstFilm.addAll(super.FACADE_FILM.findAll());

        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void modifierFilm() {
        Film film = tvFilm.getSelectionModel().getSelectedItem();

        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILM, new GererFilmController(film));


    }

    @FXML
    public void creerFilm() {
        FxmlUtils.chargerScene(Screens.SCREEN_GERER_FILM, new GererFilmController(null));

    }

    @FXML
    public void supprimerFilm() {
        try {
            FACADE_FILM.delete(this.tvFilm.getSelectionModel().getSelectedItem());
            this.chargerLstFilm();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void chargerLstFilm() {

        try {
            this.oLstFilm.clear();
            this.oLstFilm.addAll(FACADE_FILM.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private Film controlerFilmSelection() throws ViewException {
        Film film = this.tvFilm.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(film)) {
            return film;
        } else {
            throw new ViewException(ConstantesView.MSG_FILM_NON_SELECTIONNE);
        }
    }

    private ContextMenu creationMenuContextuel() {

        // création du menu contextuel
        ContextMenu contextMenu = new ContextMenu();
        // création du/des options du menu contextuel
        MenuItem itemModifier = new MenuItem("Modifier");
        MenuItem itemSupprimer = new MenuItem("Supprimer");

        // mappage de la méthode supprimer() sur le menuItem
        itemModifier.setOnAction(c -> modifierFilm());
        itemSupprimer.setOnAction(c -> supprimerFilm());


        // ajout du menuItem au contextMenu
        contextMenu.getItems().add(itemModifier);
        contextMenu.getItems().add(itemSupprimer);



        return contextMenu;

    }
    private TableRow<Film> creationRow(TableView<Film> f) {
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





}
