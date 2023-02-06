package net.ent.etrs.megamovies.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.daos.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.entities.references.GenreException;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
	
	public static Realisateur fabriquerRealisateur(final String nom, final Genre genre) throws ValidException, GenreException {
		
		Realisateur realisateur = new Realisateur();
		realisateur.setNom(nom);
		realisateur.ajouterGenre(genre);
		
		return ValidatorUtils.validate(realisateur);
	}
	
	public static Film fabriquerFilm(final String titre, final Genre genre, final LocalDate dateSortie, final Realisateur realisateur) throws ValidException {
		Film film = new Film();
		film.setTitre(titre);
		film.setGenre(genre);
		film.setDateSortie(dateSortie);
		film.setRealisateur(realisateur);
		
		return ValidatorUtils.validate(film);
	}
}
