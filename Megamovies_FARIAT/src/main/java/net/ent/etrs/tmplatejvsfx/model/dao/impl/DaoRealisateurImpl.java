package net.ent.etrs.tmplatejvsfx.model.dao.impl;

import net.ent.etrs.tmplatejvsfx.model.dao.DaoRealisateur;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DaoRealisateurImpl extends AbstractJpaDao<Realisateur, Serializable> implements DaoRealisateur {

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws DaoException {
        return new HashSet<>(
                this.em.createQuery("SELECT r FROM Realisateur r WHERE r.genres= :genre", Realisateur.class)
                        .setParameter("genre", genre)
                        .getResultList()
        );
    }
}
