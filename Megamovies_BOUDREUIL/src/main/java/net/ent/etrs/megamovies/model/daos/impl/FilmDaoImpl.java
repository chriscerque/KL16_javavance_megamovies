package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;

import java.util.HashSet;
import java.util.Set;

public class FilmDaoImpl extends AbstractJPADao<Film> implements FilmDao {

    @Override
    public Set<Film> searchFilmByReal(Realisateur realisateur) throws DaoException {

        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :realisateur", Film.class)
                            .setParameter("realisateur", realisateur)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
