package net.ent.etrs.ultramp3_GOUIN.model.daos.impl;

import net.ent.etrs.ultramp3_GOUIN.model.daos.CompositeurDAO;
import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CompositeurDAOImpl extends AbstractJpaDao<Compositeur, Long> implements CompositeurDAO {
    private static CompositeurDAOImpl dao;

    protected static CompositeurDAOImpl getInstance() {
        if (Objects.isNull(CompositeurDAOImpl.dao)) {
            CompositeurDAOImpl.dao = new CompositeurDAOImpl();
        }
        return CompositeurDAOImpl.dao;
    }

    @Override
    public Set<Compositeur> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT c FROM Compositeur c WHERE :genre member of c.genres", Compositeur.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
