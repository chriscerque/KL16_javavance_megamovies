package net.ent.etrs.megamovies.model.facade.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {

    private FilmDao filmDao;

    public FacadeMetierFilmImpl(){
        this.filmDao = DaosFactory.getFilmDao();
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());
        }catch (DaoException e ){
            throw new BusinessException(e.getMessage(),e);
        }

    }

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException {
       try {
           return this.filmDao.findByRealisateur(realisateur);
       }catch (DaoException e ){
           throw new BusinessException(e.getMessage(),e);
       }

    }

    @Override
    public void delete(final Film film) throws BusinessException {
        try {
            this.filmDao.delete(film);
        }catch (DaoException e ){
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public Film save(final Film film) throws BusinessException {
       try {
           return this.filmDao.save(film).orElseThrow(() -> new BusinessException("Could not save film"));
       }catch (DaoException e ){
           throw new BusinessException(e.getMessage(),e);
       }

    }
}
