package net.ent.etrs.megamovies.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.daos.services.ListingRealisateurService;
import net.ent.etrs.megamovies.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.view.converter.LocalDateJfxConverter;
import net.ent.etrs.megamovies.view.converter.RealisateurConverter;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.time.LocalDate;

public class GererFilmController extends AbstractController {

    @FXML
    public Film filmModif;
    @FXML
    public TextField tfTitre;
    @FXML
    public Label lblMessage;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public Button btnCreer;
    private ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();

    public GererFilmController(Film filmModif) {
        this.filmModif = filmModif;
    }

    @FXML
    public void initialize() {
        initOLstRealisateur();
        videOuPas();
        EcouterBtnValider();
        this.cbGenre.getItems().addAll(Genre.values());
        this.initComoponents();

        modificationFilm();
    }

    private void modificationFilm() {
        if (this.filmModif != null) {
            this.tfTitre.setText(this.filmModif.getTitre());
            this.dpDateSortie.setValue(this.filmModif.getDateSortie());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.cbRealisateur.setValue(this.filmModif.getRealisateur());
            this.btnCreer.setText("Modifier");
        }
        this.dpDateSortie.setConverter(new LocalDateJfxConverter());
    }

    private void initOLstRealisateur() {
        ListingRealisateurService listService = new ListingRealisateurService();

        listService.setOnSucceeded((WorkerStateEvent event) -> {
            this.oLstRealisateur = listService.getValue();
            cbRealisateur.getItems().addAll(oLstRealisateur);
            AlerteUtils.afficherMessageDansAlerte("Les réalisateur sont chargées", Alert.AlertType.INFORMATION);
        });

        listService.start();

        cbRealisateur.converterProperty().setValue(new RealisateurConverter());
        cbRealisateur.getItems().addAll(oLstRealisateur);
    }

    @FXML
    public void creer() {
        try {
            if (!verifierFormulaire()) {
                String titre = this.tfTitre.getText();
                LocalDate dateSortie = this.dpDateSortie.getValue();
                Genre genre = this.cbGenre.getSelectionModel().getSelectedItem();
                Realisateur realisateur = this.cbRealisateur.getSelectionModel().getSelectedItem();
                String action = "créé";

                if (this.filmModif != null) {
                    this.filmModif.setTitre(titre);
                    this.filmModif.setDateSortie(dateSortie);
                    this.filmModif.setGenre(genre);
                    this.filmModif.setRealisateur(realisateur);

                    action = "modifié";
                } else {
                    this.filmModif = EntitiesFactory.fabriquerFilm(genre, titre, dateSortie, realisateur);
                }

                super.FACADE_METIER.save(filmModif);
                this.lblMessage.setText(String.format("la personne a bien été %s", action));
                this.initComoponents();
            }
        } catch (ValidException e) {
            this.lblMessage.setText(e.getMapViolationsSB());
        } catch (BusinessException e) {
            this.lblMessage.setText(e.getMessage());
        }
    }

    private boolean verifierFormulaire() {
        return this.tfTitre.getText().isBlank() || dpDateSortie.getValue() == null || cbGenre.getSelectionModel().isEmpty() || cbRealisateur.getSelectionModel().isEmpty();
    }

    private void initComoponents() {
        this.tfTitre.clear();
        this.dpDateSortie.setValue(null);
        this.cbGenre.getSelectionModel().clearSelection();
        this.cbRealisateur.getSelectionModel().clearSelection();
    }

    @FXML
    public void videOuPas() {
        boolean bool = false;
        if (tfTitre.getText().isBlank() || dpDateSortie.getValue() == null || cbGenre.getSelectionModel().isEmpty() || cbRealisateur.getSelectionModel().isEmpty()) {
            bool = true;
        }
        if (bool) {
            btnCreer.setDisable(true);
        } else {
            btnCreer.setDisable(false);

        }
    }

//    @FXML
//    public void Bloquer() {
//        boolean bool = false;
//        if (tfTitre.getText().isBlank()) {
//            bool = true;
//        }
//        if (bool) {
//            dpDateSortie.setDisable(true);
//        } else {
//            dpDateSortie.setDisable(false);
//        }
//    }


//    @Override
//    public DateCell call(final DatePicker datePicker) {
//        return new DateCell() {
//            @Override
//            public void updateItem(LocalDate item, boolean empty) {
//                super.updateItem(item, empty);
//
//                // Disable Monday, Tueday, Wednesday.
//                if (item.getDayOfWeek() == DayOfWeek.MONDAY //
//                        || item.getDayOfWeek() == DayOfWeek.TUESDAY //
//                        || item.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
//                    setDisable(true);
//                }
//            }
//        };
//        return dayCellFactory;
//    }


    @FXML
    public void EcouterBtnValider() {
        tfTitre.textProperty().addListener((observable, oldValue, newValue) -> videOuPas());
        dpDateSortie.getEditor().textProperty().addListener((observable, oldValue, newValue) -> videOuPas());
        cbGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> videOuPas());
        cbRealisateur.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> videOuPas());
    }
}
