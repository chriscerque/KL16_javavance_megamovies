package net.ent.etrs.megamovies.view.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import net.ent.etrs.megamovies.controllers.AbstractController;

import java.io.IOException;
import java.util.Objects;

public class FxmlUtils {


    @Getter
    private static Scene scene = new Scene(new AnchorPane(), 600, 400);

    public static Scene initialiserScene(String name) {
        try {
            scene = new Scene(getFxmlLoader(name));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors de l''initialisation de la scene.", Alert.AlertType.ERROR);
        }
        return scene;
    }

    /**
     * Permet de charger une page fxml dans une scene en spécifiant le controller.
     *
     * @param sceneCourante
     * @param screen        page fxml
     * @param controller
     * @throws IOException
     */
    public static void chargerScene(Scene sceneCourante, String screen, Object controller) {
        try {
            scene.setRoot(getFxmlLoader(sceneCourante, screen, controller));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page." + screen, Alert.AlertType.ERROR);
        }
    }

    /**
     * Permet de charger une page fxml dans une scene en spécifiant le controller.
     *
     * @param screen     page fxml
     * @param controller
     * @throws IOException
     */
    public static void chargerScene(String screen, Object controller) {
        try {
            scene.setRoot(getFxmlLoader(null, screen, controller));
        } catch (IOException e) {
            e.printStackTrace();
            AlerteUtils.afficherMessageDansAlerte("Une erreure s'est produite lors du chargement de la page." + screen, Alert.AlertType.ERROR);
        }
    }

    private static Parent getFxmlLoader(Scene sceneCourante, String screen, Object controller) throws IOException {
        if (sceneCourante != null) {
            scene = sceneCourante;
        }
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));
        if (controller != null) {
            loader.setController(controller);
        }
        return loader.load();
    }

    private static Parent getFxmlLoader(Scene sceneCourante, String screen) throws IOException {
        if (sceneCourante != null) {
            scene = sceneCourante;
        }
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));

        return loader.load();
    }

    private static Parent getFxmlLoader(String screen) throws IOException {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(screen));

        return loader.load();
    }

    /**
     * Permet de charger une page fxml dans une scene.
     *
     * @param sceneCourante
     * @param screen        page fxml
     * @throws IOException
     */
    public static void chargerScene(Scene sceneCourante, String screen) {
        FxmlUtils.chargerScene(sceneCourante, screen, null);
    }

    /**
     * Permet de charger une page fxml dans une scene courante.
     *
     * @param screen page fxml
     * @throws IOException
     */
    public static void chargerScene(String screen) {
        FxmlUtils.chargerScene(null, screen, null);
    }

    /**
     * @param menu
     * @param title
     * @param position
     * @return a new clickable menu with a title
     */
    public static ClickableMenu createClickableMenu(final MenuBar menu, final String title, final int position) {
        ClickableMenu cm = new ClickableMenu(title);
        menu.getMenus().add(position, cm);
        return cm;
    }

    public static ClickableMenu createClickableMenu(final MenuBar menu, final String title) {
        return createClickableMenu(menu, title, 0);
    }

    public static Stage openSubScreen(final String title, final String fxmlFile, final AbstractController controller) {
        return openSubScreen(scene, title, fxmlFile, controller);
    }

    /**
     * @param scene
     * @param title
     * @param fxmlFile
     * @param controller
     * @return the stage of a new screen
     */
    public static Stage openSubScreen(final Scene scene, final String title, final String fxmlFile, final AbstractController controller) {
        try {
            // charge le fichier fxml
            FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlFile));
            if (controller != null) {
                loader.setController(controller);
            }
            Parent root = loader.load();

            // Créer la boite de dialogue
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(scene.getWindow());
            stage.setScene(new Scene(root));

            return stage;
        } catch (IOException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.NONE);
        }
        return null;
    }

    public static void openSubScreenAndWait(final Scene newScene, final String title, final String fxmlFile, final AbstractController controller) {
        Objects.requireNonNull(openSubScreen(newScene, title, fxmlFile, controller)).showAndWait();
    }

    public static void openSubScreenAndWait(final String title, final String fxmlFile, final AbstractController controller) {
        Objects.requireNonNull(openSubScreen(title, fxmlFile, controller)).showAndWait();
    }
}
