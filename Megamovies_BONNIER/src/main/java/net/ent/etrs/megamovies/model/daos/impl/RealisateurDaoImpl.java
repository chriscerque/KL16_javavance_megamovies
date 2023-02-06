package net.ent.etrs.megamovies.model.daos.impl;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur, Serializable> implements RealisateurDao {

    private static RealisateurDaoImpl dao;


    protected static RealisateurDaoImpl getInstance() {
        if (Objects.isNull(RealisateurDaoImpl.dao)) {
            RealisateurDaoImpl.dao = new RealisateurDaoImpl();
        }
        return RealisateurDaoImpl.dao;
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT r from Realisateur r WHERE r.genre = :genre", Realisateur.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
