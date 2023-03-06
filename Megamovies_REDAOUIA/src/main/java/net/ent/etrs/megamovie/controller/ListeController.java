package net.ent.etrs.megamovie.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.time.LocalDate;

public class ListeController extends AbstractController{
    public TextField listeRecherche;
    public ChoiceBox listeDeroulant;
    public TableView<Film> listeTv;
    public TableColumn<Film, String> listeTcTitre;
    public TableColumn<Film, LocalDate> listeTcSortie;
    public TableColumn<Film, Genre> listeTcGenre;

    private ObservableList<Film> olFilms = FXCollections.observableArrayList();
    private ObservableList<Realisateur> olReal = FXCollections.observableArrayList();

    private FilteredList<Film> fltFilms = new FilteredList<>(olFilms, s -> true);
    private SortedList<Film>    slFilms = new SortedList<>(fltFilms);



    public void initialize() throws BusinessException, DaoException {
        getAllFilm();
        getAllReal();
        afficherFilm();
        rechercherFilm();
    }

    private void rechercherFilm() {
        this.listeRecherche.textProperty().addListener(
                (obs, oldValue, newValue) -> fltFilms.setPredicate(titre -> predicatFiltreTitre(titre, newValue))
        );
        this.listeDeroulant.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> fltFilms.setPredicate(film -> {
                    boolean filtre  = predicatFiltreReal(newValue, film);
                    return filtre;
                }
        ));
    }

    private boolean predicatFiltreReal(Object newValue, Film film) {
        String filter = newValue.toString();
        return film.getRealisateur().getNom().contains(filter);
    }

//    private void traiterSelectionReal(Object oldValue, Object newValue) {
//        this.olFilms = new FilteredList<>()
//    }

    private boolean predicatFiltreTitre(Film titre, String newValue) {
        if (newValue == null || newValue.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    private void afficherFilm() {
       this.listeTcTitre.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getTitre()));
       this.listeTcSortie.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getDateSortie()));
       this.listeTcGenre.setCellValueFactory(a -> new SimpleObjectProperty<>(a.getValue().getGenre()));
        this.listeTv.setItems(this.olFilms);
    }

    private void getAllReal() throws BusinessException, DaoException {
        this.olReal.addAll(FACADE_REALISATEUR.readAll());

    }

    private void getAllFilm() throws BusinessException, DaoException {

            this.olFilms.addAll(FACADE_FILM.findAll());
            this.fltFilms = (new FilteredList<>(this.olFilms, s -> true));
            this.slFilms = new SortedList<>(this.slFilms);
    }
}
