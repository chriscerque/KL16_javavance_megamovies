package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {


    private static RealisateurDaoImpl realisateurDao;
    private static FilmDaoImpl filmDao;

    static {
        realisateurDao = new RealisateurDaoImpl();
        filmDao = new FilmDaoImpl();
    }

    public static RealisateurDao fabriquerRealisateurDao(){
        return realisateurDao;
    }

    public static FilmDao fabriquerFilmDao(){
        return filmDao;
    }

}

