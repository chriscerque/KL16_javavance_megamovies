package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.IFilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.jpa.AbstractJPADao;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;

import java.util.HashSet;
import java.util.Set;

public class FilmDao extends AbstractJPADao<Film> implements IFilmDao {

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException {
        try {
            return new HashSet<>(this.em.createQuery(
                    "SELECT f " +
                            "FROM Film f " +
                            "WHERE f.realisateur = :real"
                    , Film.class).setParameter("real", realisateur).getResultList());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}