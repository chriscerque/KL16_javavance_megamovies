package net.ent.etrs.megamovies.model.facade.impl;

import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.dao.DaoFilm;
import net.ent.etrs.megamovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Set;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {

    private DaoFilm dao;

    public FacadeMetierFilmImpl() {
        this.dao = DaoFactory.fabriquerDaoFilm();
    }

    @Override
    public List<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.dao.findAll());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        try {
            return this.dao.findByRealisateur(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Film film) throws BusinessException {
        try {
            this.dao.delete(film);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
            return this.dao.save(film);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
