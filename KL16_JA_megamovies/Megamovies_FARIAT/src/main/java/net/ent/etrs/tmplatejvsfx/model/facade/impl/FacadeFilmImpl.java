package net.ent.etrs.tmplatejvsfx.model.facade.impl;

import net.ent.etrs.tmplatejvsfx.model.dao.DaoFilm;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.dao.impl.DaosFactory;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.facade.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeFilmImpl implements FacadeMetierFilm {

    private DaoFilm filmDao;

    protected FacadeFilmImpl() {
        this.filmDao = DaosFactory.getFilmDao();
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException {
        try {
            return filmDao.findByRealisateur(realisateur);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Film film) throws BusinessException {
        try {
            filmDao.delete(film.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public Film save(final Film film) throws BusinessException {
        try {
            return filmDao.save(film).get();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
