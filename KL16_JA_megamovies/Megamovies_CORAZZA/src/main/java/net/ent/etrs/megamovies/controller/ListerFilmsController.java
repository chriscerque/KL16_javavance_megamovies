package net.ent.etrs.megamovies.controller;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.references.Genre;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ListerFilmsController extends AbstractController {
    @FXML
    public VBox barreMenu;
    @FXML
    public TableView id_lister_tv;
    @FXML
    public TableColumn id_lister_tc_titre;
    @FXML
    public TableColumn id_lister_tc_ds;
    @FXML
    public TableColumn id_lister_tc_genre;
    @FXML
    public TextField id_recherche_titre;
    @FXML
    public ChoiceBox<Genre> id_recherche_choicebox;

    @FXML
    public void initialize() {
        chargerGenres();
    }

    private void chargerGenres() {
        id_recherche_choicebox.getItems().setAll(Genre.values());
    }

    private void filtrerGenre() {

    }

    private void filtrerRecherche(){

    }


}
