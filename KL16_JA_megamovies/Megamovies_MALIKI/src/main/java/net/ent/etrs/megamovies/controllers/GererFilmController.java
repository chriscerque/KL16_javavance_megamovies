package net.ent.etrs.megamovies.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import net.ent.etrs.megamovies.model.daos.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.daos.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.EntitiesFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;
import net.ent.etrs.megamovies.view.utils.AlerteUtils;

import java.util.Objects;

public class GererFilmController extends AbstractController {
	@FXML
	public TextField tfTitre;
	@FXML
	public DatePicker tfDateSortie;
	@FXML
	public ChoiceBox cbGenre;
	@FXML
	public ChoiceBox cbRealisateur;
	@FXML
	public Button btnValider;
	@FXML
	public VBox barreMenu;
	
	Film FilmModifier;
	
	private GererFilmController(Film film) {
		this.FilmModifier = film;
	}
	
	public void creerFilm(ActionEvent actionEvent) {
	}
	
	private void initialiserFilm() {
		if (!Objects.isNull(this.FilmModifier)) {
			this.tfTitre.setText(this.FilmModifier.getTitre());
			this.tfDateSortie.setValue(this.FilmModifier.getDateSortie());
			this.cbGenre.setValue(this.FilmModifier.getGenre());
			
			this.btnValider.setText("Modifier");
		}
	}
	
	
	public void creerModifierFilm() {
		
		
		try {
			if (Objects.nonNull(this.FilmModifier)) {
				this.FilmModifier.setTitre(tfTitre.getText());
				this.FilmModifier.setDateSortie(tfDateSortie.getValue());
				this.FilmModifier.setGenre(cbGenre.getValue().toString());
				ValidatorUtils.validate(this.FilmModifier);
				FACADE_FILM.save(this.FilmModifier);
			} else {
				this.FilmModifier = EntitiesFactory.fabriquerFilm(tfTitre.getText());
				ValidatorUtils.validate(this.FilmModifier);
				FACADE_FILM.save(this.FilmModifier);
			}
			this.annuler();
		} catch (BusinessException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMessage(), Alert.AlertType.WARNING);
//
		} catch (ValidException e) {
			AlerteUtils.afficherMessageDansAlerte(e.getMapViolationsSB(), Alert.AlertType.WARNING);
		}
	}
	
	private void annuler() {
	}
}
