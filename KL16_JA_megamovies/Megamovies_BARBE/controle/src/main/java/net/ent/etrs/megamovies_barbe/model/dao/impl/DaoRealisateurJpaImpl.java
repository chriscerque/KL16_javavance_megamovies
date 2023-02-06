package net.ent.etrs.megamovies_barbe.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_barbe.model.dao.AbstractJpaDao;
import net.ent.etrs.megamovies_barbe.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DaoRealisateurJpaImpl extends AbstractJpaDao<Realisateur> implements DaoRealisateur {


    @Override public Set<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT r " +
                                    "FROM Realisateur r " +
                                    "WHERE r.genres = :genre", Realisateur.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override public Optional<Realisateur> findByName(String s) throws DaoException {
        try {
            return Optional.of(
                    this.em.createQuery("" +
                                    "SELECT r " +
                                    "FROM Realisateur r " +
                                    "WHERE r.nom = :nom", Realisateur.class)
                            .setParameter("nom", s)
                            .getSingleResult()
            );
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
