package net.ent.etrs.megamovie.model.daos.impl;

import net.ent.etrs.megamovie.model.daos.FilmDao;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDaoImpl extends  AbstractDao<Film, Long>  implements FilmDao {

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :real", Film.class)
                    .setParameter("real", realisateur)
                    .getResultStream().collect(Collectors.toSet())
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
