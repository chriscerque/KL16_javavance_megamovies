package net.ent.etrs.template.model.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import net.ent.etrs.template.view.references.Screens;
import net.ent.etrs.template.view.utils.ClickableMenu;
import net.ent.etrs.template.view.utils.FxmlUtils;

public class BarreMenuController {

    @FXML
    private MenuBar menu;

    @FXML
    public void initialize() {
        ClickableMenu clickableMenu = new ClickableMenu("Accueil");
        clickableMenu.setOnAction(e -> this.accueil());
        this.menu.getMenus().add(0, clickableMenu);
    }


    @FXML
    public void accueil() {
        FxmlUtils.chargerScene(Screens.ACCUEIL);
    }


    public void quitter() {
        ((Stage) (this.menu.getScene().getWindow())).close();
    }

}
