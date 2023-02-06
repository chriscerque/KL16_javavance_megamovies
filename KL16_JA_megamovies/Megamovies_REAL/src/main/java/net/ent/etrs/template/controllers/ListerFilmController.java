package net.ent.etrs.template.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.ent.etrs.template.commons.utils.jxf.FxmlUtils;
import net.ent.etrs.template.controllers.converter.ConverterRealisateur;
import net.ent.etrs.template.controllers.exceptions.ViewException;
import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;
import net.ent.etrs.template.view.references.Screens;

import java.time.LocalDate;
import java.util.Objects;

public class ListerFilmController extends AbstractController{

    @FXML
    public TextField tfRecherche;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    @FXML
    public TableView<Film> tvFilm;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDateSortie;
    @FXML
    public TableColumn<Film, Genre> tcGenre;

    private final ObservableList<Film> oLstFilm = FXCollections.observableArrayList();
    private final ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();
    private FilteredList<Film> filteredListFilm = new FilteredList<>(oLstFilm);

    @FXML
    private void initialize() throws ViewException {
        this.iniTfRecherche();
        this.initCbRealisateur();
        this.initTvFilm();
    }

    private void initTvFilm() {
        try {
            this.chargerFilm();
            tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            tcDateSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));

            this.tvFilm.setItems(this.oLstFilm);
            this.tvFilm.setRowFactory(tv -> creerRow(tv));
        } catch (ViewException e) {
            throw new RuntimeException(e);
        }
    }

    private void chargerFilm() throws ViewException {
        try {
            this.oLstFilm.clear();
            this.oLstFilm.addAll(FACADE_FILM.findAll());
        } catch (BusinessException e) {
            throw new ViewException(e);
        }
    }

    private void iniTfRecherche() {
        this.tfRecherche.textProperty().addListener(
                (obs, oldValue, newValue) -> filteredListFilm.setPredicate(film
                        -> predicatFiltreLstFilm(newValue, film))
        );
    }

    private boolean predicatFiltreLstFilm(String newValue, Film film) {
        if (Objects.isNull(newValue) || newValue.isEmpty()) {
            return true;
        }
        return film.getTitre().toUpperCase().contains(newValue.toUpperCase());
    }

    private void initCbRealisateur() throws ViewException {
        try {
            this.oLstRealisateur.addAll(FACADE_REALISATEUR.readAll());
            this.cbRealisateur.setItems(this.oLstRealisateur);
            this.cbRealisateur.setConverter(new ConverterRealisateur());
        } catch (BusinessException e) {
            throw new ViewException(e);
        }
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


        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuSupprimer = new MenuItem("Supprimer");
        MenuItem menuEditer = new MenuItem("Modifier");
        MenuItem menuVisionner = new MenuItem("Visionner");

        menuEditer.setOnAction(c -> modifier());
        menuSupprimer.setOnAction(c -> supprimer());
        menuVisionner.setOnAction(c -> visionner());

        contextMenu.getItems().add(menuSupprimer);
        contextMenu.getItems().add(menuEditer);
        contextMenu.getItems().add(menuVisionner);
        return contextMenu;
    }

    private void visionner() {
        FxmlUtils.chargerScene(Screens.VISIONNERFILM);
    }

    private void supprimer() {
        try {
            Film f = this.tvFilm.getSelectionModel().getSelectedItem();
            super.FACADE_FILM.delete(f.getId());
            this.chargerFilm();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        } catch (ViewException e) {
            throw new RuntimeException(e);
        }


    }

    private void modifier() {
        Film f = this.tvFilm.getSelectionModel().getSelectedItem();
        FxmlUtils.chargerScene(Screens.MODIFIER_FILM, new ModifierFilmController(f));

    }




}
