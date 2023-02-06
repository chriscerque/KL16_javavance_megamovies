package net.ent.etrs.megamovie.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovie.view.converter.RealisateurConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;

public class ListerFilmReaController extends AbstractController {
    private static Log logFile = LogFactory.getLog(Constantes.LOG_FILENAME);
    @FXML
    public ComboBox<Realisateur> cbRea;
    @FXML
    public Label listerFilmsRea;


    @FXML
    public void initialize() {
        this.initListenersChange();
        this.chargerRea();

    }

    @FXML
    private void chargerRea() {
        this.cbRea.setConverter(new RealisateurConverter());
        this.cbRea.setPromptText("Choissisez un realisateur : ");
        try {
            for (Realisateur realisateur : super.FACADE_METIER_REALISATEUR.selectAll()) {
                logFile.info("Realisateur = " + realisateur.getNom() + " trouver lors dune recherche");
                this.cbRea.getItems().add(realisateur);
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML

    private void initListenersChange() {
        this.cbRea.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                listerFilmsRea.setDisable(false);
                chargerFilms(newValue);
            }
        });
    }

    @FXML
    private void chargerFilms(final Realisateur newValue) {
        listerFilmsRea.setVisible(true);
        String s = " Films :  ";

        try {
            for (Film film : super.FACADE_METIER_FILM.findByRealisateur(newValue)) {
                s += film.getTitre() + "  ";
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

        listerFilmsRea.setText(s);
    }
}
