package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.IFilmDao;
import net.ent.etrs.megamovies.model.daos.IRealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    private static final IFilmDao filmDao;
    private static final IRealisateurDao realisateurDao;

    static {
        filmDao = new FilmDao();
        realisateurDao = new RealisateurDao();
    }

    public static IFilmDao getFilmDao() {
        return filmDao;
    }

    public static IRealisateurDao getRealisateurDao() {
        return realisateurDao;
    }


}

