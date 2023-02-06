package net.ent.etrs.megamovies_falgat.model.facades.impl;

import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeFilm;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.logging.FileHandler;

public class FacadeFilmImpl extends AbstractFacade<Film> implements FacadeFilm {

    private static final Logger LOG = LogManager.getLogger("logBdd");

    @Override
    public List<Film> readAllFilms() throws BusinessException {
        try {
            List<Film> list = IteratorUtils.toList(super.daoFilm.findAll().iterator());
            LOG.info("Liste extraite avec succès de la bdd");
            return list;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Film> readAllFilmsByGenre(final Genre genre) throws BusinessException {
        try {
            List<Film> list = super.daoFilm.readAllByGenre(genre);
            LOG.info("Liste extraite avec succès de la bdd");
            return list;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void save(final Film film) throws BusinessException {
        try {
            super.daoFilm.save(film);
            LOG.info("Film enregistré avec succès");
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws BusinessException {
        try {
            super.daoFilm.delete(id);
            LOG.info("Film supprimé avec succès");
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
