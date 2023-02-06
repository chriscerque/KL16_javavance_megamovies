package net.ent.etrs.tmplatejvsfx.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.tmplatejvsfx.model.dao.DaoFilm;
import net.ent.etrs.tmplatejvsfx.model.dao.DaoRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {


    private static final DaoFilm filmDao;
    private static final DaoRealisateur realisateurDao;


    static {
        filmDao = new DaoFilmImpl();
        realisateurDao = new DaoRealisateurImpl();
    }

    public static DaoFilm getFilmDao() {
        return filmDao;
    }
    public static DaoRealisateur getRealisateurDao() {
        return realisateurDao;
    }
}
