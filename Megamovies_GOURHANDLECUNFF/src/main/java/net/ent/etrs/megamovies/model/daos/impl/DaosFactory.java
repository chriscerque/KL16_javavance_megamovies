package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {
    public static FilmDao fabriquerFilmDao() {
        return FilmDaoImpl.getInstance();
    }
    public static RealisateurDao fabriquerRealisateurDao() {
        return RealisateurDaoImpl.getInstance();
    }

}
