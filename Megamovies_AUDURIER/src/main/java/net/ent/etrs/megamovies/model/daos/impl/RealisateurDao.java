package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.IRealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.jpa.AbstractJPADao;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.util.HashSet;
import java.util.Set;

public class RealisateurDao extends AbstractJPADao<Realisateur> implements IRealisateurDao {

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws DaoException {
        try {
            return new HashSet<>(this.em.createQuery(
                    "SELECT r " +
                            "FROM Realisateur r " +
                            "WHERE :genre MEMBER OF r.genres"
                    , Realisateur.class).setParameter("genre", genre).getResultList());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}