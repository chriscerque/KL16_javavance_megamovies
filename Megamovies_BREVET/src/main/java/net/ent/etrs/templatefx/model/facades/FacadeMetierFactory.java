package net.ent.etrs.templatefx.model.facades;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {

    private static final FacadeMetierFilm fmf;
    private static final FacadeMetierRealisateur fmr;

    static {
        fmf = new FacadeMetierFilmImpl();
        fmr = new FacadeMetierRealisateurImpl();
    }

    public static FacadeMetierRealisateur getFacadeMetierRealisateur() {
        return fmr;
    }

    public static FacadeMetierFilm getFacadeMetierFilm() {
        return fmf;
    }
}
