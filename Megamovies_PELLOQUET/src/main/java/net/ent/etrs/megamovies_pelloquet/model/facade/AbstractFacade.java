package net.ent.etrs.megamovies_pelloquet.model.facade;


import net.ent.etrs.megamovies_pelloquet.model.dao.DaoFactory;
import net.ent.etrs.megamovies_pelloquet.model.dao.FilmDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.RealisateurDao;

public abstract class AbstractFacade<T> {

    protected FilmDao filmDao  = DaoFactory.fabriquerDaoFilm();
    protected RealisateurDao realisateurDao  = DaoFactory.fabriquerDaoRealisateur();


}
