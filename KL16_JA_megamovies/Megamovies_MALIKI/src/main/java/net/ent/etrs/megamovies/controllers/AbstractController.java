package net.ent.etrs.megamovies.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import net.ent.etrs.megamovies.model.facades.FacadeFilm;
import net.ent.etrs.megamovies.model.facades.FacadeRealisateur;
import net.ent.etrs.megamovies.model.facades.impl.FacadeFactory;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.io.IOException;
import java.util.Objects;

public class AbstractController {
	
	public static final FacadeFilm FACADE_FILM = FacadeFactory.fabriquerFacadeFilm();
	
	public static final FacadeRealisateur FACADE_REALISATEUR = FacadeFactory.fabriquerFacadeRealisateur();
	
	
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
	
	
}
