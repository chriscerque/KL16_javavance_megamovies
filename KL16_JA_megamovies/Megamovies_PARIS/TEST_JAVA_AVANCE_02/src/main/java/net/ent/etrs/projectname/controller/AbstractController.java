package net.ent.etrs.projectname.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.projectname.model.facade.FacadeMetierFactory;
import net.ent.etrs.projectname.model.facade.FacadeMetierFilm;
import net.ent.etrs.projectname.view.C_VIEW;
import net.ent.etrs.projectname.view.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractController {

    public static final FacadeMetierFilm FACADE_FILM = FacadeMetierFactory.fabriquerFilmFacade();

    protected void chargerScene(Scene sceneCourante, String screen, Object controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
        if (!Objects.isNull(controller)) {
            loader.setController(controller);
        }
        try {
            sceneCourante.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page.", Alert.AlertType.ERROR);
        }
    }

    protected void quitter() {
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(C_VIEW.CONFIRMATION_DIALOG, C_VIEW.CONFIRMATION,
                C_VIEW.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

    public void aPropos() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by warti", Alert.AlertType.INFORMATION);
    }
}
