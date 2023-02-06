package net.ent.etrs.megamovies.model.facades.impl;


import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.FacadeFilm;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.util.Set;


public class FacadeFilmImpl extends AbstractBaseFacade<Film> implements FacadeFilm {

    private static FilmDao dao;

    public FacadeFilmImpl() {
        super(DaoFactory.fabriquerFilmDao());
        dao = (FilmDao) super.getDao();
    }

    @Override
    public Set<Film> findAllByRealisateur(final Realisateur realisateur) throws BusinessException {
        try {
            return dao.searchFilmByReal(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
