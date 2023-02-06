package net.ent.etrs.megamovies_falgat.model.dao.impl;

import lombok.extern.java.Log;
import net.ent.etrs.megamovies_falgat.model.dao.DaoFilm;
import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;

public class DaoFilmImpl extends AbstractJpaDao<Film, Serializable> implements DaoFilm {

    @Override
    public List<Film> readAllByGenre(final Genre genre) throws DaoException {
        try {
            return this.em.createQuery("SELECT f FROM Film f WHERE f.genre = :genre", Film.class)
                    .setParameter("genre", genre)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


}
