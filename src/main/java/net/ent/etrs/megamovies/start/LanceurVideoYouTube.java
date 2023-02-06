package net.ent.etrs.megamovies.start;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.ent.etrs.megamovies.commons.utils.jxf.AlerteUtils;

public class LanceurVideoYouTube extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

//            System.setProperty("http.proxyHost", "172.16.0.1");
//            System.setProperty("http.proxyPort", "8080");
//            System.setProperty("https.proxyHost", "172.16.0.1");
//            System.setProperty("https.proxyPort", "8080");

            primaryStage.setTitle("Teaser");

            WebView webview = new WebView();
            WebEngine webEngine = webview.getEngine();
            webEngine.load("https://youtu.be/uyP70r9PS6A");
            webview.setPrefSize(640, 390);
            // Update the stage title when a new web page title is available
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        //stage.setTitle(webEngine.getLocation());
                        primaryStage.setTitle(webEngine.getTitle());
                    }
                }
            });

            ProgressBar progressBar = new ProgressBar();
            // A Worker load the page
            Worker<Void> worker = webEngine.getLoadWorker();
            // Bind the progress property of ProgressBar
            // with progress property of Worker
            progressBar.progressProperty().bind(worker.progressProperty());

            // Create the VBox
            VBox root = new VBox();
            // Add the WebView to the VBox
            root.getChildren().addAll(webview, progressBar);
            // Set the Style-properties of the VBox
            root.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: blue;");

            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
        }

    }

}
