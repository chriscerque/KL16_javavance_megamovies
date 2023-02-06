package net.ent.etrs.templatefx.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.Genre;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;
import net.ent.etrs.templatefx.view.converter.DateConverter;
import net.ent.etrs.templatefx.view.converter.RealisateurConverter;
import net.ent.etrs.templatefx.view.references.ConstantesView;
import net.ent.etrs.templatefx.view.utils.AlerteUtils;

import java.time.LocalDate;

public class VoirFilmographieController extends AbstractController {
    @FXML
    public TableView<Film> tvFilm;
    @FXML
    public TableColumn<Film, String> tcTitre;
    @FXML
    public TableColumn<Film, LocalDate> tcDate;
    @FXML
    public TableColumn<Film, Genre> tcGenre;
    @FXML
    public Label lblRealisateur;
    @FXML
    public ComboBox<Realisateur> cbReal;

    private ObservableList<Realisateur> oLstRealisateurs = FXCollections.observableArrayList();

    private Realisateur realisateurSelec;

    private ObservableList<Film> oLstFilms = FXCollections.observableArrayList();

    private FilteredList<Film> filteredLstFilm = new FilteredList<>(this.oLstFilms);

    public VoirFilmographieController(final Realisateur realisateurSelec) {
        this.realisateurSelec = realisateurSelec;
    }

    public void initialize() {
        this.initTvFilm();
        this.initCbReal();
        this.initLblRealisateur();
    }

    private void initCbReal() {
        try {
            this.oLstRealisateurs.clear();
            this.oLstRealisateurs.addAll(this.fmr.readAll());
            this.cbReal.setCellFactory(cb -> new TextFieldListCell<>(new RealisateurConverter()));
            this.cbReal.setItems(oLstRealisateurs);
            this.cbReal.valueProperty().addListener(new ChangeListener<Realisateur>() {
                @Override
                public void changed(final ObservableValue<? extends Realisateur> observableValue, final Realisateur oldValue, final Realisateur newValue) {
                    lblRealisateur.setText(ConstantesView.LABEL_FILMO_BASE + newValue.);
                    filteredLstFilm.setPredicate(film -> predicatFiltreFilm(newValue, film));
                }
            });
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }

    private boolean predicatFiltreFilm(final Realisateur newValue, final Film film) {
        return film.getRealisateur() == newValue;
    }

    private void initLblRealisateur() {
        this.lblRealisateur.setText(ConstantesView.LABEL_FILMO_BASE + realisateurSelec.getNom());
    }

    private void initTvFilm() {
        this.chargerListeFilms();
        this.filteredLstFilm.setPredicate(film -> predicatFiltreFilm(realisateurSelec, film));
        this.tvFilm.setItems(this.filteredLstFilm);

        this.tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));
        this.tcDate.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));
        this.tcDate.setCellFactory(tc -> new TextFieldTableCell<>(new DateConverter()));
        this.tcGenre.setCellValueFactory((param) -> new SimpleObjectProperty<>(param.getValue().getGenre()));
    }

    private void chargerListeFilms() {
        try {
            this.oLstFilms.clear();
            this.oLstFilms.addAll(super.fmf.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }
    }
}
