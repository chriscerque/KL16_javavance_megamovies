package net.ent.etrs.templatefx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import net.ent.etrs.templatefx.commons.validator.ValidException;
import net.ent.etrs.templatefx.model.entities.EntitiesFactory;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.Genre;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;
import net.ent.etrs.templatefx.view.converter.RealisateurConverter;
import net.ent.etrs.templatefx.view.utils.AlerteUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class AjouterFilmController extends AbstractController {

    @FXML
    public TextField tfTitre;
    @FXML
    public DatePicker dpDateSortie;
    @FXML
    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    @FXML
    public Button btnConfirmer;
    @FXML
    public Label lblMessage;
    public Film filmModif;
    private ObservableList<Genre> oLstGenre = FXCollections.observableArrayList();
    private ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();

    public AjouterFilmController(final Film filmModif) {
        this.filmModif = filmModif;
    }

    @FXML
    public void initialize() {
        this.initOLst();
        this.initCb();
        this.initDate();
        if (filmModif != null) {
            this.tfTitre.setText(this.filmModif.getTitre());
            this.dpDateSortie.setValue(this.filmModif.getDateSortie());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.cbRealisateur.setValue(this.filmModif.getRealisateur());
            this.btnConfirmer.setText("Modifier");
        } else {
            this.disabled();
        }
    }

    private void initDate() {
        this.dpDateSortie.setDayCellFactory(new Callback<DatePicker, DateCell>() {
                                                @Override
                                                public DateCell call(final DatePicker datePicker) {
                                                    return new DateCell() {
                                                        @Override
                                                        public void updateItem(LocalDate item, boolean empty) {
                                                            super.updateItem(item, empty);
                                                            if (!(item.getDayOfWeek() == DayOfWeek.WEDNESDAY)) {
                                                                setDisable(true);
                                                            }
                                                        }
                                                    };
                                                }
                                            }
        );
    }

    private void initCb() {
        this.cbGenre.setItems(this.oLstGenre);
        this.cbRealisateur.setCellFactory(cb -> new TextFieldListCell<>(new RealisateurConverter()));
        this.cbRealisateur.setItems(this.oLstRealisateur);
    }

    private void initOLst() {
        try {
            this.oLstGenre.clear();
            this.oLstGenre.addAll(Genre.values());
            this.oLstRealisateur.clear();
            this.oLstRealisateur.addAll(super.fmr.readAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private void disabled() {
        this.btnConfirmer.setDisable(true);
        this.dpDateSortie.setDisable(true);
        this.cbGenre.setDisable(true);
        this.cbRealisateur.setDisable(true);
        this.initListenerTitre();
        this.initListenerDate();
        this.initListenerGenre();
        this.initListenerRealisateur();
    }

    private void initListenerRealisateur() {
        this.cbRealisateur.valueProperty().addListener(new ChangeListener<Realisateur>() {
            @Override
            public void changed(final ObservableValue<? extends Realisateur> observableValue, final Realisateur oldValue, final Realisateur newValue) {
                if (!(Objects.isNull(dpDateSortie.getValue()) || Objects.isNull(cbGenre.getValue()) || tfTitre.getText().isBlank())) {
                    btnConfirmer.setDisable(newValue == null);
                }
            }
        });
    }

    private void initListenerGenre() {
        this.cbGenre.valueProperty().addListener(new ChangeListener<Genre>() {
            @Override
            public void changed(final ObservableValue<? extends Genre> observableValue, final Genre oldValue, final Genre newValue) {
                try {
                    cbRealisateur.setDisable(newValue == null);
                    oLstRealisateur.clear();
                    oLstRealisateur.addAll(fmr.findByGenre(newValue)); //TODO BDD pas ouf
                    if (!(Objects.isNull(dpDateSortie.getValue()) || Objects.isNull(cbRealisateur.getValue()) || tfTitre.getText().isBlank())) {
                        btnConfirmer.setDisable(newValue == null);
                    }
                } catch (BusinessException e) {
                    AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
                }
            }
        });
    }

    private void initListenerDate() {
        this.dpDateSortie.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(final ObservableValue<? extends LocalDate> observableValue, final LocalDate oldValue, final LocalDate newValue) {
                cbGenre.setDisable(newValue == null);
                if (!(Objects.isNull(cbGenre.getValue()) || Objects.isNull(cbRealisateur.getValue()) || tfTitre.getText().isBlank())) {
                    btnConfirmer.setDisable(newValue == null);
                }
            }
        });
    }

    private void initListenerTitre() {
        this.tfTitre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) {
                if (Objects.isNull(dpDateSortie.getValue())) {
                    dpDateSortie.setDisable(newValue.isBlank());
                }
                if (!(Objects.isNull(dpDateSortie.getValue()) || Objects.isNull(cbGenre.getValue()) || Objects.isNull(cbRealisateur.getValue()))) {
                    btnConfirmer.setDisable(newValue.isBlank());
                }
            }
        });
    }

    @FXML
    public void creer() {
        try {
            String titre = this.tfTitre.getText();
            LocalDate dateSortie = this.dpDateSortie.getValue();
            Genre genre = this.cbGenre.getValue();
            Realisateur realisateur = this.cbRealisateur.getValue();

            String action = "créé";

            if (this.filmModif != null) {
                this.filmModif.setTitre(titre);
                this.filmModif.setDateSortie(dateSortie);
                this.filmModif.setGenre(genre);
                this.filmModif.setRealisateur(realisateur);
                action = "modifié";
            } else {
                this.filmModif = EntitiesFactory.fabriquerFilm(titre, dateSortie, genre, realisateur);
            }

            super.fmf.save(filmModif);

            this.lblMessage.setText(String.format("Le client a bien été %s", action));
        } catch (ValidException e) {
            StringBuilder sb = new StringBuilder();
            for (String titre : e.mapViolations.keySet()) {
                for (String contrainte : e.mapViolations.get(titre)) {
                    sb.append(titre);
                    sb.append(" : ");
                    sb.append(contrainte);
                    sb.append("\n");
                }
            }
            AlerteUtils.afficherMessageDansAlerte(sb.toString(), Alert.AlertType.ERROR);
        } catch (BusinessException e) {
            this.lblMessage.setText(e.getMessage());
        }
    }
}
