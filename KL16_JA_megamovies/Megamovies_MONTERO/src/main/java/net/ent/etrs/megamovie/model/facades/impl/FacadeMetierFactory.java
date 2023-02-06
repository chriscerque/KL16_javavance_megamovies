package net.ent.etrs.megamovie.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovie.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovie.model.facades.FacadeMetierRealisateur;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacadeMetierFactory {
    private static FacadeMetierFilm FilmFm;
    private static FacadeMetierRealisateur RealisateurFm;


    static {
        FilmFm = new FacadeFilmImpl();
        RealisateurFm = new FacadeRealisateurImpl();

    }

    public static FacadeMetierFilm fabriquerFmFilm() {
        return FilmFm;
    }


    public static FacadeMetierRealisateur fabriquerFmRealisateur() {
        return RealisateurFm;
    }
}
