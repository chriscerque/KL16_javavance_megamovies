package net.ent.etrs.megamovies_falgat.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_falgat.model.dao.DaoFilm;
import net.ent.etrs.megamovies_falgat.model.dao.DaoRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    private static final DaoFilm filmDao;
    private static final DaoRealisateur realisateurDao;

    static {
        filmDao = new DaoFilmImpl();
        realisateurDao = new DaoRealisateurImpl();
    }

    public static DaoFilm getDaoFilm() {
        return filmDao;
    }

    public static DaoRealisateur getDaoRealisateur() {
        return realisateurDao;
    }

}
