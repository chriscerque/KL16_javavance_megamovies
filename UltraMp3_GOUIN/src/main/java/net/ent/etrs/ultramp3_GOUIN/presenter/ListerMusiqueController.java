package net.ent.etrs.ultramp3_GOUIN.presenter;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierFactory;
import net.ent.etrs.ultramp3_GOUIN.model.facades.exceptions.BusinessException;
import net.ent.etrs.ultramp3_GOUIN.view.utils.AlerteUtils;

public class ListerMusiqueController extends AbstractController{


    private final ObservableList<Musique> olstVoitures = FXCollections.observableArrayList();
    @FXML
    private TableView<Musique> tvMusique;
    @FXML
    private TableColumn<Musique, String> tcTitre;
    @FXML
    private TableColumn<Musique, String> tcDateSortie;
    @FXML
    private TableColumn<Musique, String> tcGenre;

    @FXML
    private TextField tfRecherche;

    private ComboBox<Genre> cbGenre;

    @FXML
    public void initialize() {
        facadeMetierMusique = FacadeMetierFactory.fabriquerFacadeMetierMusique();
        initListMusique();
        initTvMusique();
    }

    private void initTvMusique() {
        // Rien de visuel
        this.tvMusique.setItems(olstVoitures);

        // Avec les lambdas JAVA 8
        tcTitre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitre()));

//        tcDateSortie.setCellValueFactory(
//                (param) -> new SimpleObjectProperty<>(param.getValue().getDateSortie()));

        tcGenre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGenre().toString()));
    }

    private void initListMusique() {
        try {
            olstVoitures.addAll(facadeMetierMusique.findAll());
        } catch (BusinessException e) {
            AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

}
