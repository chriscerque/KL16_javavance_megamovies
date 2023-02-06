package net.ent.etrs.megamovie.model.dao.impl;


import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class FilmDao extends AbstractJPADao<Film> implements net.ent.etrs.megamovie.model.dao.FilmDao {

    @Override
    public Iterable<Film> findFilmsByRealisateur(final Realisateur realisateur) throws DaoException {
        try {
            TypedQuery<Film> result = this.em.createQuery("SELECT film FROM Film film left join film.realisateur fr where fr = :rea ", Film.class);
            result.setParameter("rea", realisateur);
            return result.getResultList();
        } catch (NoResultException e) {
            throw new DaoException(e);
        }

    }
}