package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IteratorUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class FacadeMetierFilmImpl extends AbstractFacade<Film, Serializable> implements FacadeMetierFilm {
    private FilmDao dao;

    public FacadeMetierFilmImpl() {
        this.dao = DaosFactory.fabriquerFilmDao();
    }
    /**
     * @param realisateur
     * @return
     * @throws BusinessException
     */
    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        try {
            return Set.copyOf(IteratorUtils.toList(this.dao.findByRealisateur(realisateur).iterator()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @throws BusinessException
     */
    @Override
    public void initialisationFilm() throws BusinessException {

    }

    /**
     * @param genre
     * @return
     * @throws BusinessException
     */
    @Override
    public Set<Film> findByGenre(Genre genre) throws BusinessException {
        try {
            return Set.copyOf(IteratorUtils.toList(this.dao.findByGenre(genre).iterator()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @param dateSortie
     * @return
     * @throws BusinessException
     */
    @Override
    public Set<Film> findByDateSortie(LocalDate dateSortie) throws BusinessException {
        try {
            return Set.copyOf(IteratorUtils.toList(this.dao.findByDateSortie(dateSortie).iterator()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @param titre
     * @return
     * @throws BusinessException
     */
    @Override
    public Optional<Film> findByTitre(String titre) throws BusinessException {
        try {
            return this.dao.findByTitre(titre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
