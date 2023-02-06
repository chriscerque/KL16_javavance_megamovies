package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmDaoImpl extends AbstractJpaDao<Film, Serializable> implements FilmDao {

    private static FilmDaoImpl dao;

    protected static FilmDaoImpl getInstance() {
        if (Objects.isNull(FilmDaoImpl.dao)) {
            FilmDaoImpl.dao = new FilmDaoImpl();
        }
        return FilmDaoImpl.dao;
    }


    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException {
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
