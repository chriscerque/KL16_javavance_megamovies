package net.ent.etrs.megamovies.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.IFilmFm;
import net.ent.etrs.megamovies.model.facades.IRealisateurFm;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacadeMetierFactory {
    private static IFilmFm filmFm;
    private static IRealisateurFm realisateurFm;


    static {
        filmFm = new FilmFmImpl();
        realisateurFm = new RealisateurFmImpl();

    }

    public static IFilmFm fabriquerFmFilm() {
        return filmFm;
    }

    public static IRealisateurFm fabriquerFmRealisateur() {
        return realisateurFm;
    }


}
