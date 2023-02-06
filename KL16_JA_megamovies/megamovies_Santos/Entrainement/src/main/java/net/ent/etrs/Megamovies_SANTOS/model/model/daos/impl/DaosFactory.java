package net.ent.etrs.Megamovies_SANTOS.model.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.Megamovies_SANTOS.model.model.daos.FilmDao;
import net.ent.etrs.Megamovies_SANTOS.model.model.daos.RealisateurDao;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {
    public static RealisateurDao fabriquerRealisateurDao() {
        return RealisateurDaoImpl.getInstance();
    }

    public static FilmDao fabriquerFilmDao() {
        return FilmDaoImpl.getInstance();
    }

}
