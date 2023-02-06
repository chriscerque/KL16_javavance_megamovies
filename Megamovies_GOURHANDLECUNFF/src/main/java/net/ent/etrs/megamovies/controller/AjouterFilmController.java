package net.ent.etrs.megamovies.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;
import net.ent.etrs.megamovies.view.utils.Screens;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AjouterFilmController extends AbstractController {

    @FXML
    VBox barreMenu;

    @FXML
    TextField tfTitre;

    @FXML
    DatePicker dpDateSortie;

    @FXML
    ChoiceBox<Genre> cbGenre;

    @FXML
    ChoiceBox<Realisateur> cbRealisateur;

    @FXML
    Button btnCreerModifier;

    @FXML
    Button btnAnnuler;

    Film filmAModifier;

    protected AjouterFilmController(Film client) {
        this.filmAModifier = client;
    }

    @FXML
    public void initialize() {
        try {
            cbGenre.setItems(FXCollections.observableArrayList(Genre.values()));
            cbRealisateur.setItems((ObservableList<Realisateur>) FACADE_REALISATEUR.findByGenre(cbGenre.getValue()));
            initialiserClient();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void initialiserClient() {
        if (!Objects.isNull(this.filmAModifier)) {
            this.tfTitre.setText(this.filmAModifier.getTitre());
            this.dpDateSortie.setValue(this.filmAModifier.getDateSortie());
            this.cbGenre.setValue(this.filmAModifier.getGenre());
            this.cbRealisateur.setValue(this.filmAModifier.getRealisateur());

            this.btnCreerModifier.setText("Modifier");
        }
    }

    public void creerModifierFilm() {
        try {
            if (Objects.nonNull(this.filmAModifier)) {
                this.filmAModifier.setTitre(tfTitre.getText());
                this.filmAModifier.setDateSortie(dpDateSortie.getValue());
                this.filmAModifier.setGenre(((Genre) cbGenre.getValue()));
                this.filmAModifier.setRealisateur(((Realisateur) cbRealisateur.getValue()));
                ValidatorUtils.validate(this.filmAModifier);
                FACADE_FILM.save(this.filmAModifier);
            } else {
                this.filmAModifier = EntitiesFactory.fabriquerFilm(tfTitre.getText(), ((Realisateur) cbRealisateur.getValue()), dpDateSortie.getValue(), ((Genre) cbGenre.getValue()));
                ValidatorUtils.validate(this.filmAModifier);
                FACADE_FILM.save(this.filmAModifier);
            }

            this.annuler();
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
        } catch (ValidException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMapViolationsSB(), Alert.AlertType.WARNING);
        }
    }

    public void annuler() {
        this.chargerScene(this.barreMenu.getScene(), Screens.SCREEN_ACCUEIL, null);
    }
}
