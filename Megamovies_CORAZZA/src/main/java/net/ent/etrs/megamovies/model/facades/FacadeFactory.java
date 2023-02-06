package net.ent.etrs.megamovies.model.facades;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facades.impl.FacadeFilmImpl;
import net.ent.etrs.megamovies.model.facades.impl.FacadeRealisateurImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {
    public static FacadeMetierFilm fabriquerFacadeFilm() {
        return new FacadeFilmImpl();
    }

    public static FacadeMetierRealisateur fabriquerFacadeRealisateur() {
        return new FacadeRealisateurImpl();
    }
}
