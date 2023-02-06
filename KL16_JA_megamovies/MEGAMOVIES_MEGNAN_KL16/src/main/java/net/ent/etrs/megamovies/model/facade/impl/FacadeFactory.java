package net.ent.etrs.megamovies.model.facade.impl;

import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;

public final class FacadeFactory {

    public static FacadeMetierFilm fabriquerFacadeMetierFilm() {
        return new FacadeMetierFilmImpl();
    }

    public static FacadeMetierRealisateur fabriquerFacadeMetierRealisateur() {
        return new FacadeMetierRealisateurImpl();
    }
}
