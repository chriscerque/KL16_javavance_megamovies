package net.ent.etrs.megamovies.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.atomic.AtomicBoolean;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GererFilmController extends AbstractController {

    @FXML
    public VBox barreMenu;
    @FXML
    public TextField id_gerer_titre;
    @FXML
    public DatePicker id_gerer_date;
    @FXML
    public MenuButton id_gerer_genre;
    @FXML
    public MenuButton id_gerer_realisateur;
    @FXML
    public Button id_gerer_valider;

    @FXML
    public void initialize() {
        initIdGererDate();
        initIdGererGenre();
        initIdGererRealiserateur();
        initidgerervalider();
    }


    private void initIdGererDate() {
        id_gerer_titre.textProperty().addListener((observable, oldValue, newValue) -> {
            id_gerer_date.setDisable(!verifierCompletion(newValue));
        });
    }

    private void initIdGererGenre() {
        id_gerer_date.valueProperty().addListener((observable, oldValue, newValue) -> {
            id_gerer_genre.setDisable(!verifierCompletion(String.valueOf(newValue)));
        });
    }

    private void initIdGererRealiserateur() {
        id_gerer_genre.textProperty().addListener((observable, oldValue, newValue) -> {
            id_gerer_realisateur.setDisable(!verifierCompletion(newValue));
        });
    }

    private void initidgerervalider() {
        id_gerer_realisateur.textProperty().addListener((observable, oldValue, newValue) -> {
            id_gerer_valider.setDisable(!verifierCompletion(newValue));
        });
    }


    private boolean verifierCompletion(String newValue) {
        if ((newValue == null || newValue.isEmpty()))
            return false;
        else
            return true;
    }




}
