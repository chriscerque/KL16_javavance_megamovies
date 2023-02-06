package net.ent.etrs.megamovie.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    private static final FilmDao filmDao;
    private static final RealisateurDao realisateurDao;

    static {
        filmDao = new FilmDao();
        realisateurDao = new RealisateurDao();
    }

    public static FilmDao getFilmDao() {
        return filmDao;
    }

    public static RealisateurDao getRealisateurDao() {
        return realisateurDao;
    }
}

