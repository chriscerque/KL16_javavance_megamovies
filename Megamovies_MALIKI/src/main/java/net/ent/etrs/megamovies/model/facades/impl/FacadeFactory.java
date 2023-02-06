package net.ent.etrs.megamovies.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.FacadeFilm;
import net.ent.etrs.megamovies.model.facades.FacadeRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacadeFactory {
	
	public static FacadeFilm fabriquerFacadeFilm() {
		return new FacadeFilmImpl();
	}
	
	public static FacadeRealisateur fabriquerFacadeRealisateur() {
		return new FacadeRealisateurImpl();
	}
}
