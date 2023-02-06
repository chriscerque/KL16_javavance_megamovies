package net.ent.etrs.megamovies_barbe.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_barbe.model.dao.AbstractJpaDao;
import net.ent.etrs.megamovies_barbe.model.dao.DaoFilm;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;

import java.util.HashSet;
import java.util.Set;

// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DaoFilmJpaImpl extends AbstractJpaDao<Film> implements DaoFilm {


    @Override public Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f " +
                                    "FROM Film f " +
                                    "WHERE f.realisateur = :realisateur", Film.class)
                            .setParameter("realisateur", realisateur)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
