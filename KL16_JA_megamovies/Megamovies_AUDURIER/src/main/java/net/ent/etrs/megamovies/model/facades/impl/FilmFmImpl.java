package net.ent.etrs.megamovies.model.facades.impl;


import net.ent.etrs.megamovies.model.daos.IFilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exception.BusinessException;
import net.ent.etrs.megamovies.model.facades.AbstractBaseFM;
import net.ent.etrs.megamovies.model.facades.IFilmFm;

import java.util.Set;

public class FilmFmImpl extends AbstractBaseFM<Film> implements IFilmFm {
    private final IFilmDao dao;

    protected FilmFmImpl() {
        super(DaoFactory.getFilmDao());
        this.dao = (IFilmDao) super.getDao();
    }

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException {
        try {
            return this.dao.findByRealisateur(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}