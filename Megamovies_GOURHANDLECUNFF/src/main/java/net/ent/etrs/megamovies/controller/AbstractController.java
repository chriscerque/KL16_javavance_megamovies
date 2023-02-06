package net.ent.etrs.megamovies.controller;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facades.impl.FacadeFactory;
import net.ent.etrs.megamovies.view.references.ConstanteView;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe abstraite pour les controller.
 */
public abstract class AbstractController {

    /**
     * Facade film.
     */
    public static final FacadeMetierFilm FACADE_FILM = FacadeFactory.getFacadeFilm();
    /**
     * Facade réalisateur.
     */
    public static final FacadeMetierRealisateur FACADE_REALISATEUR = FacadeFactory.getFacadeRealisateur();

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
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstanteView.CONFIRMATION_DIALOG, ConstanteView.CONFIRMATION,
                ConstanteView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

    public void aPropos() {
        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by java", Alert.AlertType.INFORMATION);
    }


}
