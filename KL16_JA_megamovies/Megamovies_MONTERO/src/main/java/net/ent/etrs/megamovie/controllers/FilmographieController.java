package net.ent.etrs.megamovie.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FilmographieController extends AbstractController {
    @FXML
    public WebView wvFilmVideo;


    private String url = "https://www.youtube.com/watch?v=";
    @FXML
    private WebEngine engine;

    public FilmographieController() {
    }

    public FilmographieController(final String id) {

        if (id == null) {
            //generation dun url random
            url = "https://www.youtube.com/watch?v=BiPUoLI9eGM";
        } else {
            //concatenation de L'id a lur dune video youtube
            this.url += id;
        }
    }

    @FXML
    public void initialize() {

        engine = wvFilmVideo.getEngine();
        System.setProperty("http.proxyHost", "172.16.0.1");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "172.16.0.1");
        System.setProperty("https.proxyPort", "8080");
        engine.load(url);

    }


}
