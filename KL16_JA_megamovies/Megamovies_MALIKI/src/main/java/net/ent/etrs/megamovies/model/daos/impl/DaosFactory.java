package net.ent.etrs.megamovies.model.daos.impl;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaosFactory {
	
	
	public static RealisateurDao fabriquerDaoRealisateur() {
		return new RealisateurDaoImpl();
	}
	
	public static FilmDao fabriquerDaoFilm() {
		return new FilmDaoImpl();
	}
}


