package net.ent.etrs.Megamovies_SANTOS.model.model.facades.impl;

import net.ent.etrs.Megamovies_SANTOS.model.model.daos.FilmDao;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Film;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.DaoException;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierFilm;
import org.apache.commons.collections.IteratorUtils;

import java.util.Iterator;
import java.util.Set;

public class FacadeFilmImpl implements FacadeMetierFilm {
    private FilmDao dao;
    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return (Set<Film>) IteratorUtils.toList((Iterator) dao.findAll());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        return (Set<Film>) this.dao.findByRealisateur(realisateur);
    }

    @Override
    public void delete(Film film) throws BusinessException  {
        try {
            this.dao.delete(film.getId());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
          return  this.dao.save(film);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
