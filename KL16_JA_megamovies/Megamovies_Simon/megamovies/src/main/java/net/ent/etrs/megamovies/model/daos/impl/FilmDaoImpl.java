package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDaoImpl extends AbstractJpaDao<Film> implements FilmDao {

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException {

        try {
            TypedQuery<Film> tp = this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :realisateur", Film.class);
            tp.setParameter("realisateur", realisateur);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}

