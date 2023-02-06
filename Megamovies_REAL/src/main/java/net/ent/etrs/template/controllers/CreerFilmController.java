package net.ent.etrs.template.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.ent.etrs.template.commons.utils.validator.ValidException;
import net.ent.etrs.template.controllers.converter.ConverterDatePicker;
import net.ent.etrs.template.controllers.converter.ConverterRealisateur;
import net.ent.etrs.template.controllers.exceptions.ViewException;
import net.ent.etrs.template.model.entities.EntitiesFactory;
import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;


public class CreerFilmController extends AbstractController{

    @FXML
    public TextField tfTitre;
    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;

    private final ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    private final ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();
    private Film film;

    @FXML
    private void initialize() throws ViewException {
        this.initCbGenre();
        this.initCbRealisateur();
        this.datePickerBlocker();
    }

    private void datePickerBlocker(){
        dpDateSortie.setDayCellFactory(new ConverterDatePicker());
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

    private void initCbGenre() {
        this.oLstGenre.addAll(Genre.values());
        this.cbGenre.setItems(this.oLstGenre);
    }

    @FXML
    public void valider(ActionEvent actionEvent) throws ViewException {
        try {
            this.film = EntitiesFactory.fabriquerFilm(tfTitre.getText(), cbGenre.getValue(), dpDateSortie.getValue(),cbRealisateur.getValue());
            FACADE_FILM.save(this.film);
        } catch (BusinessException | ValidException e) {
            throw new ViewException(e);
        }
    }
}
