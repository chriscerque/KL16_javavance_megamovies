package net.ent.etrs.megamovies_pelloquet.model.facade.impl;

import net.ent.etrs.megamovies_pelloquet.model.dao.DaoException;
import net.ent.etrs.megamovies_pelloquet.model.entities.Film;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.facade.AbstractFacade;
import net.ent.etrs.megamovies_pelloquet.model.facade.Exceptions.BusinessException;
import net.ent.etrs.megamovies_pelloquet.model.facade.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl extends AbstractFacade<Film> implements FacadeMetierFilm {
    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        return this.filmDao.findByRealisateur(realisateur);
    }

    @Override
    public void delete(Film film) throws BusinessException {
        try {
            this.filmDao.delete(film.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
            return this.filmDao.save(film).get();
        } catch (NoSuchElementException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
