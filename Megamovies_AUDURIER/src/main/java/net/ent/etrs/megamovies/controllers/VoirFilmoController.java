package net.ent.etrs.megamovies.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exception.BusinessException;
import net.ent.etrs.megamovies.view.references.converter.FilmsStringConverter;
import net.ent.etrs.megamovies.view.references.converter.RealisateurStringConverter;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import org.apache.commons.collections4.IterableUtils;

import java.util.Objects;

public class VoirFilmoController extends AbstractController {
    @FXML
    public ComboBox<Realisateur> cbReal;
    @FXML
    public ListView<Film> lvFilms;
    private ObservableList<Realisateur> oListReal = FXCollections.observableArrayList();
    private ObservableList<Film> oListFilms = FXCollections.observableArrayList();
    private Realisateur realisateurSelec;

    public VoirFilmoController(final Realisateur realisateurSelec) {
        if (Objects.nonNull(realisateurSelec)) {
            this.realisateurSelec = realisateurSelec;
        }//TODO bon bah ca marche pas, la combo box est maudite


    }

    public void initialize() {
        this.initCbReal();
        this.initLvFilms();
        if (this.realisateurSelec != null) {
            this.cbReal.getSelectionModel().select(this.realisateurSelec);
//            this.chargerFilms();
        }
    }

    private void initLvFilms() {
//        this.chargerFilms();
        this.lvFilms.setItems(this.oListFilms);
        this.lvFilms.setCellFactory((param) -> new TextFieldListCell<>(new FilmsStringConverter()));
    }

    private void chargerFilms() {
        System.out.println("realisateurSelec : " + realisateurSelec);
        if (!Objects.isNull(this.realisateurSelec)) {
            try {
                this.oListFilms.clear();
                System.out.println("realisateurSelec $$$$$$$: " + realisateurSelec);
                this.oListFilms.addAll(IterableUtils.toList(FACADE_METIER_FILM.findByRealisateur(this.realisateurSelec)));
                System.out.println("oListFilms : " + oListFilms);
            } catch (BusinessException e) {
                AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
            }
        }
    }

    private void initCbReal() {
        try {
            this.oListReal.addAll(IterableUtils.toList(FACADE_METIER_REALISATEUR.selectAll()));
            this.cbReal.getItems().addAll(oListReal);
            this.cbReal.setConverter(new RealisateurStringConverter());
            System.out.println("toto//////////////////////");
//            this.cbReal.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println("realisateurSelec 2 : " + realisateurSelec);
//                this.realisateurSelec = newValue;
//                this.chargerFilms();
//            });
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
        }
    }

}
