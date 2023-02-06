package net.ent.etrs.megamovies.controllers;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facade.impl.FacadeFactory;
import net.ent.etrs.megamovies.view.C_VIEW;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe abstraite pour les controller.
 *
 * @author christophe.cerqueira
 */
public abstract class AbstractController {

    /**
     * Facade 1.
     */
    public static final FacadeMetierFilm FACADE_FILM = FacadeFactory.fabriquerFacadeMetierFilm();
    /**
     * Facade 2.
     */
    public static final FacadeMetierRealisateur FACADE_REALISATEUR = FacadeFactory.fabriquerFacadeMetierRealisateur();

    /**
     * Permet de charger une page fxml dans une scene en spécifiant le controller.
     *
     * @param sceneCourante
     * @param screen        page fxml
     * @param controller
     * @throws IOException
     */
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
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by China?", Alert.AlertType.INFORMATION);
    }


}
