package net.ent.etrs.megamovie.model.facades.impl;

import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.util.HashSet;
import java.util.Set;

public class FacadeFilmImpl extends AbstractFacade implements FacadeMetierFilm {
    @Override
    public Set<Film> findAll() throws BusinessException, DaoException {
        Set<Film> films = new HashSet<>();
        for (Film f: this.filmDao.findAll()) {
            films.add(f);
        };
        return films;
    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException, DaoException {
        return this.filmDao.findByRealisateur(realisateur);
    }

    @Override
    public void delete(Film film) throws BusinessException, DaoException {
        this.filmDao.delete(film);
    }

    @Override
    public Film save(Film film) throws BusinessException, DaoException {
        return this.filmDao.save(film).orElse(null);    }
}
