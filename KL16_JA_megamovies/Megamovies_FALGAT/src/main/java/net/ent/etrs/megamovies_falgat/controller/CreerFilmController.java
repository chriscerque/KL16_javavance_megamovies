package net.ent.etrs.megamovies_falgat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_falgat.commons.utils.AlerteUtils;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidException;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies_falgat.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_falgat.view.converters.DatePickerConverter;
import net.ent.etrs.megamovies_falgat.view.converters.RealisateurJFXConverter;
import net.ent.etrs.megamovies_falgat.view.references.ConstanteView;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CreerFilmController extends AbstractController{

    @FXML
    private Button btValider;

    @FXML
    private TextField tfTitre;

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox<Genre> cbGenre;

    @FXML
    private ComboBox<Realisateur> cbRealisateur;

    private ObservableList<Genre> oLstGenres = FXCollections.observableArrayList();

    private ObservableList<Realisateur> oLstRealisateurs = FXCollections.observableArrayList();

    private Film filmModif;

    private Boolean modeModification = false;

    public CreerFilmController(Film filmModif) {
        this.filmModif = filmModif;
        if(!Objects.isNull(filmModif)){
            this.modeModification = true;
        }
    }

    public void initialize(){

        this.disableElements();
        this.initCbGenre();
        this.initCbRealisateur();
        this.initDpDate();
        this.initCreationModif();
        
    }

    private void initDpDate() {
        this.dpDate.setShowWeekNumbers(true);
        Callback<DatePicker, DateCell> dayCellFactory = DatePickerConverter.getDayCellFactory();
        this.dpDate.setDayCellFactory(dayCellFactory);
    }


    private void initCreationModif(){
        if(this.modeModification){
            this.tfTitre.setText(this.filmModif.getTitre());
            this.dpDate.setValue(this.filmModif.getDateSortie());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.cbRealisateur.setValue(this.filmModif.getRealisateur());

            this.btValider.setText(ConstanteView.BTN_MODIFIER);
        } else {
            this.cbRealisateur.setPromptText(ConstanteView.PROMPT_SELECTIONNER);
            this.cbGenre.setPromptText(ConstanteView.PROMPT_SELECTIONNER);
        }

    }

    public void initCbRealisateur() {
        try{

            this.oLstRealisateurs.addAll(super.facadeRealisateur.readAllRealisateur());
            this.cbRealisateur.converterProperty().setValue(new RealisateurJFXConverter());
            this.cbRealisateur.setItems(this.oLstRealisateurs);

        } catch(BusinessException e){
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void initCbGenre() {
        this.oLstGenres.addAll(Genre.values());
        this.cbGenre.setItems(oLstGenres);
    }

    public void valider(){

        String titre = this.tfTitre.getText();
        LocalDate dateSortie = this.dpDate.getValue();
        Genre genre = this.cbGenre.getValue();
        Realisateur realisateur = this.cbRealisateur.getValue();



        try {
            if(this.modeModification){
                this.filmModif.setTitre(titre);
                this.filmModif.setDateSortie(dateSortie);
                this.filmModif.setGenre(genre);
                this.filmModif.setRealisateur(realisateur);
            } else {
                this.filmModif = EntitiesFactory.fabriquerFilm(titre, genre, dateSortie, realisateur);
            }

            ValidatorUtils.validate(this.filmModif);

            super.facadeFilm.save(this.filmModif);

            this.initFormulaire();

        } catch (ValidException | BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void activateDpDate(){
        if(this.tfTitre.getText().length() > 1) {
            this.dpDate.disableProperty().set(false);
        }
    }

    public void activateCbRealisateur(){
        try {
            if(!Objects.isNull(this.cbGenre.getValue())){
                this.cbRealisateur.disableProperty().set(false);
                this.oLstRealisateurs.clear();
                this.oLstRealisateurs.addAll(super.facadeRealisateur.readAllRealisateurByGenre(this.cbGenre.getSelectionModel().getSelectedItem()));
            }
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

    public void activateCbGenre(){
        if(!Objects.isNull(this.dpDate)){
            this.cbGenre.disableProperty().set(false);
        }
    }


    public void activateButton(){
        if(!Objects.isNull(this.cbRealisateur.getValue())){
            this.btValider.disableProperty().set(false);
        }
    }

    private void disableElements(){
        this.dpDate.disableProperty().set(true);
        this.cbGenre.disableProperty().set(true);
        this.cbRealisateur.disableProperty().set(true);
        this.btValider.disableProperty().set(true);
    }

    private void initFormulaire() {

        this.tfTitre.clear();
        this.cbGenre.setValue(null);
        this.cbRealisateur.setValue(null);
        this.dpDate.setValue(null);

        this.disableElements();

    }


}
