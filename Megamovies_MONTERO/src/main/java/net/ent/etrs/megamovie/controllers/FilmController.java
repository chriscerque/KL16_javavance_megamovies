package net.ent.etrs.megamovie.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import net.ent.etrs.megamovie.commons.validator.ValidException;
import net.ent.etrs.megamovie.model.entities.EntititesFactory;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.entities.references.Genre;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovie.view.converter.GenreConverter;
import net.ent.etrs.megamovie.view.converter.LocalDateJfxConverter;
import net.ent.etrs.megamovie.view.converter.RealisateurConverter;
import net.ent.etrs.megamovie.view.references.ConstantesView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class FilmController extends AbstractController {

    private static Log logFile = LogFactory.getLog(Constantes.LOG_FILENAME);
    @FXML
    public TextField tfTitre;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRea;
    @FXML
    public Label lblMessage;
    @FXML
    public Label lblListeFilm;

    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public Button btnValider;

    private Film filmModif;


    public FilmController(Film filmModif) {
        this.filmModif = filmModif;
    }

    @FXML
    public void initialize() {
        this.initComoponents();
        this.initListenersChange();

        if (this.filmModif != null) {
            this.tfTitre.setText(this.filmModif.getTitre());
            this.dpDateSortie.setValue(this.filmModif.getDateSortie());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.cbRea.setValue(this.filmModif.getRealisateur());
            this.btnValider.setText("Modifier");
        }

        this.dpDateSortie.setConverter(new LocalDateJfxConverter());

//        this.btnCreer.setDisable(true);
    }

    @FXML
    private void initListenersChange() {
        this.tfTitre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String s, final String t1) {
                if (!t1.isBlank()) {
//                    System.out.println(t1);
                    dpDateSortie.setDisable(false);
                }
            }
        });
        this.dpDateSortie.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(final ObservableValue<? extends LocalDate> observableValue, final LocalDate localDate, final LocalDate t1) {
                if (!Objects.isNull(t1)) {
                    cbGenre.setDisable(false);
                }
            }

        });

        this.cbGenre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                this.chargerRea();
                cbRea.setDisable(false);
            }
        });

        this.cbRea.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                btnValider.setDisable(false);
                chargerFilms(newValue);
            }
        });
    }

    @FXML
    private void chargerFilms(final Realisateur newValue) {
        lblListeFilm.setDisable(false);
        String s = " Films :  ";

        try {
            for (Film film : super.FACADE_METIER_FILM.findByRealisateur(newValue)) {
                s += film.getTitre() + "  ";
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

        lblListeFilm.setText(s);
    }

    @FXML
    public void creer() {
        try {

            if (!verifierFormulaire()) {

                String titre = this.tfTitre.getText();
                LocalDate date = this.dpDateSortie.getValue();
                Genre genre = this.cbGenre.getValue();
                Realisateur realisateur = this.cbRea.getValue();

                String action = "créé";

                if (this.filmModif != null) {
                    this.filmModif.setTitre(titre);
                    this.filmModif.setDateSortie(date);
                    this.filmModif.setGenre(genre);
                    this.filmModif.setRealisateur(realisateur);
                    action = "modifié";
                } else {
                    this.filmModif = EntititesFactory.fabriquerFilm(date, genre, titre, realisateur);
                }

                super.FACADE_METIER_FILM.save(filmModif);
                logFile.info("Film = " + filmModif.getTitre() + " sauvegarde");

                this.lblMessage.setText(String.format("le film a bien été %s", action));
                this.initComoponents();
            }
        } catch (ValidException e) {
            this.lblMessage.setText(e.getMapViolationsSB());
        } catch (BusinessException e) {
            this.lblMessage.setText(e.getMessage());
        }

    }

    @FXML
    private boolean verifierFormulaire() {
        return this.tfTitre.getText().isBlank() ||
                Objects.isNull(this.dpDateSortie.getValue()) ||
                Objects.isNull(this.cbRea.getValue()) ||
                Objects.isNull(this.cbGenre.getValue());
    }

    @FXML
    private void initComoponents() {
        this.tfTitre.clear();


        this.dpDateSortie.setValue(null);
        //ne laisser la possilite de choisir seulement mercredi
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        dpDateSortie.setDayCellFactory(dayCellFactory);
        this.cbGenre.setConverter(new GenreConverter());
        this.cbGenre.setPromptText(ConstantesView.SELECT_GENRE_MSG);
        this.cbRea.setPromptText(ConstantesView.SELECT_REALISATEUR_MSG);

        this.cbGenre.getItems().addAll(Genre.values());


    }

    @FXML
    private void chargerRea() {
        this.cbRea.setConverter(new RealisateurConverter());
        try {
            for (Realisateur realisateur : super.FACADE_METIER_REALISATEUR.findByGenre(this.cbGenre.getValue())) {
                logFile.info("Realisateur = " + realisateur.getNom() + " trouver lors dune recherche");
                this.cbRea.getItems().add(realisateur);
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.getDayOfWeek() == DayOfWeek.MONDAY
                                || item.getDayOfWeek() == DayOfWeek.THURSDAY
                                || item.getDayOfWeek() == DayOfWeek.TUESDAY
                                || item.getDayOfWeek() == DayOfWeek.SATURDAY
                                || item.getDayOfWeek() == DayOfWeek.FRIDAY
                                || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
