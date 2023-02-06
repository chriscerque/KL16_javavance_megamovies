package net.ent.etrs.megamovie.model.facades.impl;

import net.ent.etrs.megamovie.model.daos.FilmDao;
import net.ent.etrs.megamovie.model.daos.GenreDao;
import net.ent.etrs.megamovie.model.daos.RealisateurDao;
import net.ent.etrs.megamovie.model.daos.impl.DaoFactory;

public class AbstractFacade {
    protected FilmDao filmDao;
    protected GenreDao genreDao;
    protected RealisateurDao realisateurDao;

    protected AbstractFacade() {
        this.filmDao = DaoFactory.fabriquerFilmDao();
        this.realisateurDao = DaoFactory.fabriquerRealisateurDao();
    }
}
