package net.ent.etrs.Megamovies_SANTOS.model.model.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.NoArgsConstructor;
import net.ent.etrs.Megamovies_SANTOS.model.model.commons.validator.ValidException;
import net.ent.etrs.Megamovies_SANTOS.model.model.controllers.converter.LocalDateJfxConverter;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.EntitiesFactory;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Film;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;

import javax.xml.transform.Source;
import java.time.LocalDate;


@NoArgsConstructor
public class GererFilmController extends AbstractController {
    @FXML
    public Button btnCreer;
    @FXML
    public TextField tftitre;
    @FXML
    public DatePicker dpDateSortie;

    @FXML

    public ComboBox<Genre> cbGenre;
    @FXML
    public ComboBox<Realisateur> cbRealisateur;
    public Label lblMessage;

    private Film filmModif;


    public GererFilmController(Film filmModif) {
        this.filmModif = filmModif;
    }

    public void initialize() throws BusinessException {
        ecoute();
        initComoponents();


        this.cbGenre.getItems().addAll(Genre.values());
        System.out.println(FACADE_METIER_REALISATEUR.readAll());
        this.cbRealisateur.getItems().addAll(FACADE_METIER_REALISATEUR.readAll());


        if (this.filmModif != null) {
            this.tftitre.setText(this.filmModif.getTitre());


            this.dpDateSortie.setValue(this.filmModif.getDateSortie());
            this.cbRealisateur.setValue(this.filmModif.getRealisateur());
            this.cbGenre.setValue(this.filmModif.getGenre());
            this.dpDateSortie.setConverter(new LocalDateJfxConverter());
            this.btnCreer.setText("Modifier");
        }
    }
        public void creer () {
            try {

                if (!verifierFormulaire()) {

                    String nom = this.tftitre.getText();
                    Genre genre = this.cbGenre.getSelectionModel().getSelectedItem();
                    Realisateur realisateur = this.cbRealisateur.getSelectionModel().getSelectedItem();
                    LocalDate dateSortie = this.dpDateSortie.getValue();


                    String action = "créé";


                    if (this.filmModif != null) {

                        this.filmModif.setGenre(genre);

                        this.filmModif.setDateSortie(dateSortie);
                        this.filmModif.setRealisateur(realisateur);
                        this.filmModif.setGenre(genre);
                        this.filmModif.setTitre(nom);
                        action = "modifié";
                    } else {
                        this.filmModif = EntitiesFactory.fabriquerFilm(dateSortie, genre, nom, realisateur);
                    }

                    FACADE_FILM.save(filmModif);

                    this.lblMessage.setText(String.format("le film a bien été %s", action));

                }
            } catch (ValidException e) {
                this.lblMessage.setText(e.getMapViolationsSB());
            } catch (BusinessException e) {
                this.lblMessage.setText(e.getMessage());
            }

        }

    private boolean verifierFormulaire() {
        return this.tftitre.getText().isBlank() || this.cbRealisateur.getItems().isEmpty() || this.cbGenre.getItems().isEmpty();
    }




        private void initComoponents() {
            this.tftitre.clear();

            this.cbRealisateur.getSelectionModel().clearSelection();
            this.cbGenre.getSelectionModel().clearSelection();
            cbRealisateur.setDisable(false);
            cbGenre.setDisable(false);
            btnCreer.setDisable(false);
            dpDateSortie.setDisable(false);
        }


        public void cPasVide() {
            boolean bool = false;

            if (!tftitre.getText().isBlank()){
                dpDateSortie.setDisable(true);
               // cbGenre.setDisable(true);
                if (!(dpDateSortie.getValue() ==null)){
                   cbGenre.setDisable(true);
                    if (!cbGenre.getSelectionModel().isEmpty()){
                        cbRealisateur.setDisable(true);
                    }
                }
            }
            if (tftitre.getText().isBlank() || dpDateSortie.getValue() == null || cbGenre.getSelectionModel().isEmpty() || cbRealisateur.getSelectionModel().isEmpty()) {
                bool = true;
            }
            if (bool) {
                btnCreer.setDisable(true);
            } else {
                btnCreer.setDisable(false);

            }


        }
        public void ecoute() {
            tftitre.textProperty().addListener((observable, oldValue, newValue) -> cPasVide());
            dpDateSortie.getEditor().textProperty().addListener((observable, oldValue, newValue) -> cPasVide());
            cbRealisateur.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> cPasVide());
            cbGenre.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> cPasVide());
        }



    private void etape3() {
        cbRealisateur.setDisable(true);
    }

    private  void etape2() {
         dpDateSortie.setDisable(true);
    }

}


