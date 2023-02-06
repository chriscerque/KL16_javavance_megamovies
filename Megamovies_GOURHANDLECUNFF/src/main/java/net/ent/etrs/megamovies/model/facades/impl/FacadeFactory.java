package net.ent.etrs.megamovies.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {
    public static FacadeMetierFilm getFacadeFilm() {
        return new FacadeMetierFilmImpl();
    }

    public static FacadeMetierRealisateur getFacadeRealisateur() {
        return new FacadeMetierRealisateurImpl();
    }
}
