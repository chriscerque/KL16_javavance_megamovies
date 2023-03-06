package net.ent.etrs.megamovie.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.EntitiesFactory;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;
import net.ent.etrs.megamovie.view.utils.AlerteUtils;

import java.time.LocalDate;

public class AjoutController extends AbstractController{
    public TextField textTitre;
    public DatePicker dateSortie;
    public ChoiceBox<Genre> genreBox;
    public ChoiceBox<Realisateur> realBox;
    public Button valider;

    public Film f;





    public void valide(ActionEvent actionEvent)  {
        dateSortie.isDisabled();
        String titre = textTitre.getText();
        LocalDate lDdateSortie = dateSortie.getValue();
        Genre genre = genreBox.getSelectionModel().getSelectedItem();
        Realisateur real = realBox.getSelectionModel().getSelectedItem();

        try{
            f=EntitiesFactory.fabriquerFilm(lDdateSortie, titre, genre, real);
            FACADE_FILM.save(this.f);
        } catch (BusinessException | DaoException e) {

            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
