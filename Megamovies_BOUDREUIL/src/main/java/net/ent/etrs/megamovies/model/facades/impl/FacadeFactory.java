package net.ent.etrs.megamovies.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.FacadeFilm;
import net.ent.etrs.megamovies.model.facades.FacadeRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    private static FacadeRealisateurImpl facadeRealisateur;
    private static FacadeFilmImpl facadeFilm;

    static {
        facadeRealisateur = new FacadeRealisateurImpl();
        facadeFilm = new FacadeFilmImpl();
    }

    public static FacadeRealisateur fabriquerFacadeRealisateur() {
        return facadeRealisateur;
    }

    public static FacadeFilm fabriquerFacadeFilm() {
        return facadeFilm;
    }
}

