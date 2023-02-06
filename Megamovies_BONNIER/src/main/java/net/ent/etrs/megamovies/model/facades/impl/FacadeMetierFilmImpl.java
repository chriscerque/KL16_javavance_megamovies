package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.exceptions.DaoException;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {

    private FilmDao dao;

    public FacadeMetierFilmImpl() {
        this.dao = DaosFactory.fabriquerFilmDao();
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.dao.findAll()).stream().collect(Collectors.toSet());
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
    public void delete(final Long pId) throws BusinessException {
        try {
            this.dao.delete(pId);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
            return this.dao.save(film);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
