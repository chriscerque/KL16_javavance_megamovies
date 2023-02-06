package net.ent.etrs.megamovie.controller;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovie.controller.references.ConstantesView;
import net.ent.etrs.megamovie.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovie.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovie.model.facades.impl.FacadeFilmImpl;
import net.ent.etrs.megamovie.model.facades.impl.FacadeRealisateurImpl;
import net.ent.etrs.megamovie.view.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractController {
    /**
     * Facade Client.
     */
    public final static FacadeMetierRealisateur FACADE_REALISATEUR =new FacadeRealisateurImpl();
    /**
     * Facade Virement.
     */
    public static final FacadeMetierFilm FACADE_FILM = new FacadeFilmImpl();



//    @Getter(AccessLevel.PROTECTED)
//    @Setter(AccessLevel.PROTECTED)
//    private static Client clientEnCours;

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
        boolean sortie = AlerteUtils.afficherMessageDansAlerte(ConstantesView.CONFIRMATION_DIALOG, ConstantesView.CONFIRMATION,
                ConstantesView.CONFIRMATION_QUITTER, Alert.AlertType.CONFIRMATION);

        if (sortie) {
            System.out.println("Sortie de l'application...");
            Platform.exit();
        }
    }

//    public void aPropos() {
//        AlerteUtils.afficherMessageDansAlerte("Information Dialog", "Copyright", "Made by warti", AlertType.INFORMATION);
//    }

}
