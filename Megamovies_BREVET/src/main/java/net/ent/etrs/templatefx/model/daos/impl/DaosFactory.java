package net.ent.etrs.templatefx.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.templatefx.model.daos.FilmDao;
import net.ent.etrs.templatefx.model.daos.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {

    private static final FilmDao filmDao;
    private static final RealisateurDao realisateurDao;

    static {
        filmDao = new FilmDaoImpl();
        realisateurDao = new RealisateurDaoImpl();
    }

    public static RealisateurDao getRealisateurDao() {
        return realisateurDao;
    }

    public static FilmDao getFilmDao() {
        return filmDao;
    }
}
