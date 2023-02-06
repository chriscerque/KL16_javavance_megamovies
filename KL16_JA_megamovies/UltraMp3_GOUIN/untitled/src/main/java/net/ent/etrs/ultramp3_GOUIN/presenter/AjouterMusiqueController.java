package net.ent.etrs.ultramp3_GOUIN.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.ultramp3_GOUIN.commons.validator.ValidException;
import net.ent.etrs.ultramp3_GOUIN.commons.validator.ValidatorUtils;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.EntitiesFactory;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;
import net.ent.etrs.ultramp3_GOUIN.model.facades.exceptions.BusinessException;
import net.ent.etrs.ultramp3_GOUIN.view.references.ConstanteView;
import net.ent.etrs.ultramp3_GOUIN.view.utils.AlerteUtils;

import java.time.LocalDate;

public class AjouterMusiqueController extends AbstractController{

    Musique musique;
    @FXML
    private TextField tfTitre;

    @FXML
    private DatePicker dpDateSortie;

    @FXML
    private ComboBox<Genre> cbGenre;

    @FXML
    private ComboBox<Compositeur> cbCompositeur;

    @FXML
    private Button btnValider;


    @FXML
    public void ajouter() {

        String immatSaisi = tfTitre.getText();
        LocalDate dateMecChoisie = dpDateSortie.getValue();
        Genre marqueChoisie = cbGenre.getSelectionModel().getSelectedItem();
      //  facadeMetierMusique.save(musique);



    }
}
