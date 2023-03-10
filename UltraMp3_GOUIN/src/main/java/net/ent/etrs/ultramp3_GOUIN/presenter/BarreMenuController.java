package net.ent.etrs.ultramp3_GOUIN.presenter;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class BarreMenuController extends AbstractController {

    @FXML
    private VBox laBarre;

    @FXML
    private MenuItem listerMusique;

    @FXML
    private MenuItem ajouterMusique;

    @FXML
    private MenuItem quitter;

    @FXML
    public void listerMusique() {

//        FxmlUtils.chargerScene(laBarre.getScene(), Screens.SCREEN_LISTER_VOITURES, null);

    }

    @FXML
    public void ajouterMusique() {

//        FxmlUtils.chargerScene(laBarre.getScene(), Screens.SCREEN_GERER_VOITURE, new GererVoitureController(null));
    }


    @FXML
    public void aide() {
        super.aide();
    }

}
