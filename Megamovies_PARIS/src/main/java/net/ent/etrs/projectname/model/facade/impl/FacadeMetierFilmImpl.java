package net.ent.etrs.projectname.model.facade.impl;

import net.ent.etrs.projectname.model.dao.exception.DaoException;
import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.exceptions.BusinessException;
import net.ent.etrs.projectname.model.facade.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public final class FacadeMetierFilmImpl extends AbstractFacade<Film> implements FacadeMetierFilm {


    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            e.printStackTrace();
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
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
