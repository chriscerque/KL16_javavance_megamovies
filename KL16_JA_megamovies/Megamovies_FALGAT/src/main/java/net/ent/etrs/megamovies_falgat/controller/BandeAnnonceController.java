package net.ent.etrs.megamovies_falgat.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BandeAnnonceController {

    @FXML
    private WebView wvAnnonce;

    private String lien;

    public BandeAnnonceController(String lien) {
        this.lien = lien;
    }

    public void initialize(){
        System.setProperty("http.proxyHost", "172.16.0.1");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "172.16.0.1");
        System.setProperty("https.proxyPort", "8080");
        this.wvAnnonce.getEngine().load(this.lien);
    }
}
