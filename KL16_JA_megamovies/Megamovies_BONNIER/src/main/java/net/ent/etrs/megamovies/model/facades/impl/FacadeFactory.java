package net.ent.etrs.megamovies.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    public static FacadeMetierRealisateur fabriquerFacadeMetierRealisateur() {
        return new FacadeMetierRealisateurImpl();
    }

    public static FacadeMetierFilm fabriquerFacadeMetierFilm() {
        return new FacadeMetierFilmImpl();
    }
}
