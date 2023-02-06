package net.ent.etrs.ultramp3_GOUIN.model.daos.impl;

import net.ent.etrs.ultramp3_GOUIN.model.daos.MusiqueDAO;
import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MusiqueDAOImpl extends AbstractJpaDao<Musique, Long>implements MusiqueDAO {

    private static MusiqueDAOImpl dao;
    protected static MusiqueDAOImpl getInstance() {
        if (Objects.isNull(MusiqueDAOImpl.dao)) {
            MusiqueDAOImpl.dao = new MusiqueDAOImpl();
        }
        return MusiqueDAOImpl.dao;
    }

    @Override
    public Set<Musique> findByCompositeur(Compositeur compositeur) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT m FROM Musique m WHERE m.compositeur = :compositeur", Musique.class)
                            .setParameter("compositeur", compositeur)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
