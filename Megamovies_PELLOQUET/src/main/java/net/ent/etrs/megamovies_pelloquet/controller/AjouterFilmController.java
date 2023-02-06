package net.ent.etrs.megamovies_pelloquet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import net.ent.etrs.megamovies_pelloquet.commons.validator.ValidException;
import net.ent.etrs.megamovies_pelloquet.controller.references.ConstantesController;
import net.ent.etrs.megamovies_pelloquet.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies_pelloquet.model.entities.Film;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;
import net.ent.etrs.megamovies_pelloquet.model.facade.Exceptions.BusinessException;
import net.ent.etrs.megamovies_pelloquet.view.converter.DatePickerConverter;
import net.ent.etrs.megamovies_pelloquet.view.converter.RealisateurConverter;
import net.ent.etrs.megamovies_pelloquet.view.utils.AlerteUtils;
import net.ent.etrs.megamovies_pelloquet.view.utils.Screens;

import java.util.Objects;

public class AjouterFilmController extends AbstractController {
    @FXML
    AnchorPane laBase;
    @FXML
    TextField tfTitre;
    @FXML
    ComboBox<Genre> cbbGenre;
    @FXML
    ComboBox<Realisateur> cbbRealisatueur;
    @FXML
    DatePicker dpDateSortie;
    @FXML
    Button btnValider;

    ObservableList<Genre> oListGenre = FXCollections.observableArrayList();

    ObservableList<Realisateur> oListRealisateur = FXCollections.observableArrayList();
    FilteredList<Realisateur> fListRealisateur = new FilteredList<>(oListRealisateur, c -> true);


    @FXML
    private void initialize() {
        dpDateSortie.setDisable(true);
        cbbGenre.setDisable(true);
        cbbRealisatueur.setDisable(true);
        btnValider.setDisable(true);
        initFieldTitre();
        initFieldDate();
        initFieldGenre();
        initFieldRealisateur();
    }

    private void initFieldRealisateur() {
        try {
            this.cbbRealisatueur.setConverter(new RealisateurConverter());
            initOLstRealisateur();
            initFLstRealisateur();
            cbbRealisatueur.setItems(fListRealisateur);
            cbbRealisatueur.valueProperty().addListener((observable, oldValue, newValue) -> {
                verifRemplissageRealisateur(btnValider);
            });
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(ConstantesController.ERREUR_INIT, Alert.AlertType.WARNING);
        }
    }


    private void initFLstRealisateur() {
        cbbGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
            fListRealisateur.setPredicate(Realisateur -> {
                return predicateFiltreLstRealisateur(newValue, Realisateur);
            });
        });
    }

    private boolean predicateFiltreLstRealisateur(Genre newValue, Realisateur realisateur) {
        if (Objects.isNull(newValue)) {
            return true;
        }
        return realisateur.getGenres().contains(newValue);
    }

    private void initOLstRealisateur() throws BusinessException {
        this.oListRealisateur.addAll(FACADE_REALISATEUR.readAll());
    }

    private void initFieldGenre() {
        initOLstGenre();
        cbbGenre.setItems(oListGenre);
        cbbGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifRemplissageGenre(cbbRealisatueur);
        });
    }


    private void initOLstGenre() {
        this.oListGenre.addAll(Genre.values());
    }

    private void initFieldDate() {
        Callback<DatePicker, DateCell> dayCellFactory = DatePickerConverter.getDayCellFactory();
        dpDateSortie.setDayCellFactory(dayCellFactory);
        dpDateSortie.valueProperty().addListener((observable, oldValue, newValue) -> {
            verifRemplissageDp(cbbGenre);
        });
    }

    private void initFieldTitre() {
        tfTitre.textProperty().addListener((observable, oldValue, newValue) -> {
            verifRemplissageTf(dpDateSortie);
        });
    }

    //pas trouvé de moyen de faire de la généricité
    private void verifRemplissageDp(ComboBox<Genre> cbbGenre) {
        if (Objects.isNull(dpDateSortie.getValue())) {
            cbbGenre.setDisable(true);
        } else {
            cbbGenre.setDisable(false);
        }
    }

    private void verifRemplissageTf(DatePicker dp) {
        if (tfTitre.getText().isEmpty()) {
            dp.setDisable(true);
        } else {
            dp.setDisable(false);
        }
    }

    private void verifRemplissageGenre(ComboBox<Realisateur> cbbRealisatueur) {
        if (Objects.isNull(cbbGenre.getValue())) {
            cbbRealisatueur.setDisable(true);
        } else {
            cbbRealisatueur.setDisable(false);
        }
    }

    private void verifRemplissageRealisateur(Button button) {
        if (Objects.isNull(cbbRealisatueur.getValue())) {
            button.setDisable(true);
        } else {
            button.setDisable(false);
        }
    }


    public void ValiderFilm(ActionEvent actionEvent) {
        try {
            Film film = FACADE_FILM.save(EntitiesFactory.fabriquerFilm(tfTitre.getText(), dpDateSortie.getValue(), cbbGenre.getValue(), cbbRealisatueur.getValue()));
        } catch (BusinessException | ValidException e) {
            AlerteUtils.afficherMessageDansAlerte(ConstantesController.ERREUR_CREATION, Alert.AlertType.WARNING);
        }
        this.chargerScene(this.laBase.getScene(), Screens.SCREEN_LISTER_FILMS, null);
    }
}
