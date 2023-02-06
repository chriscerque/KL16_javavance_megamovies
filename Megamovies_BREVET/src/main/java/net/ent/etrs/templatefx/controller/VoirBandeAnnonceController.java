package net.ent.etrs.templatefx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VoirBandeAnnonceController {

    @FXML
    public WebView wvVideo;
    @FXML
    public ProgressBar pbChargement;

    public void initialize() {
        WebEngine webEngine = wvVideo.getEngine();
        webEngine.load("https://www.youtube.com/watch?v=U-DyU9ew9v4");
    }
}
