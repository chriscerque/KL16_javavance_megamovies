package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur, Serializable> implements RealisateurDao {
    private static RealisateurDaoImpl dao;

    protected static RealisateurDaoImpl getInstance() {
        if (Objects.isNull(RealisateurDaoImpl.dao)) {
            RealisateurDaoImpl.dao = new RealisateurDaoImpl();
        }
        return RealisateurDaoImpl.dao;
    }
    /**
     * @param nom
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<Realisateur> findByNom(String nom) throws DaoException {
        try {
            return Optional.of(
                    this.em.createQuery("SELECT r FROM Realisateur r WHERE r.nom = :nom", Realisateur.class)
                            .setParameter("nom", nom)
                            .getSingleResult()
            );
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * @param genre
     * @return
     * @throws DaoException
     */
    @Override
    public Iterable<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT r FROM Realisateur r WHERE :genre member of r.genres", Realisateur.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
