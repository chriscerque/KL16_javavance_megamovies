package net.ent.etrs.megamovie.model.daos.impl;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovie.model.daos.FilmDao;
import net.ent.etrs.megamovie.model.daos.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    public static FilmDao fabriquerFilmDao() {
        return new FilmDaoImpl();
    }

    public static RealisateurDao fabriquerRealisateurDao() {
        return new RealisateurDaoImpl();
    }



}
