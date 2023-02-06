package net.ent.etrs.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class VisualiserFilmController {

    @FXML
    public WebView wvFilm;

    public void initialize(){
        System.setProperty("http.proxyHost", "172.16.0.1");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "172.16.0.1");
        System.setProperty("https.proxyPort", "8080");
        this.wvFilm.getEngine().load("https://www.youtube.com/watch?v=uyP70r9PS6A");
    }
}
