package net.ent.etrs.projectname.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.projectname.commons.validator.ValidException;
import net.ent.etrs.projectname.model.entities.EntitiesFactory;
import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.entities.reference.Genre;

import java.time.chrono.AbstractChronology;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)


public class GererFilmController {

    final ObservableList<Film> oLstFilms = FXCollections.observableArrayList();

    final ObservableList<Realisateur> oLstRealisateurs = FXCollections.observableArrayList();


    @FXML
    TextField tfNom;

    @FXML
    DatePicker dpDateSortie;

    @FXML
    ComboBox<Genre> cbxGenre;

    @FXML
    ComboBox<Realisateur> cbxRealisateur;

    @FXML
    Button btnValider;

    Film filmAModifier;

    @FXML
    AnchorPane gererFilm;

    @FXML
    FilteredList<Film> filteredListFilms = new FilteredList<>(oLstFilms, p -> true);

    @FXML
    FilteredList<Realisateur> filteredListRealisateur = new FilteredList<>(oLstRealisateurs);

    protected GererFilmController (Film film){
        this.filmAModifier = film;
    }

    protected GererFilmController(){
    }

    @FXML
    public void initialize() {
        initialiserFilm();
    }

    private void initialiserFilm() {
        if (!Objects.isNull(this.filmAModifier)){
            this.tfNom.setText(this.filmAModifier.getTitre());
            this.dpDateSortie.setValue(this.filmAModifier.getDateSortie());
            this.cbxGenre.setValue(this.filmAModifier.getGenre());
            this.cbxRealisateur.setValue(this.filmAModifier.getRealisateur());
        }
    }


    public void valider() throws ValidException {
        if (Objects.nonNull(this.filmAModifier)){
            this.filmAModifier.setTitre(tfNom.getText());
            this.filmAModifier.setDateSortie(dpDateSortie.getValue());
            this.filmAModifier.setGenre(cbxGenre.getValue());
            this.filmAModifier.setRealisateur(cbxRealisateur.getValue());
        }else {
            this.filmAModifier = EntitiesFactory.fabriquerFilm( dpDateSortie.getValue(),cbxGenre.getValue(), tfNom.getText(),cbxRealisateur.getValue());
        }
    }

}
