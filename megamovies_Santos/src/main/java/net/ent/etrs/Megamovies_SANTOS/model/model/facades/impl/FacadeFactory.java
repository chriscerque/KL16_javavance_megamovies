package net.ent.etrs.Megamovies_SANTOS.model.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierFilm;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierRealisateur;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    public static FacadeMetierRealisateur fabriquerFacadeRealisateur() {
        return new FacadeRealisateurImpl();
    }

    public static FacadeMetierFilm fabriquerFacadeFilm() {
        return new FacadeFilmImpl();
    }

}
