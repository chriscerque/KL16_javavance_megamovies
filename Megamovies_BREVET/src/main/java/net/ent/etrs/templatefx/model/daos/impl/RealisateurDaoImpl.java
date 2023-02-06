package net.ent.etrs.templatefx.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.templatefx.model.daos.RealisateurDao;
import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.Genre;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur, Serializable> implements RealisateurDao {
    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws DaoException {
        try {
            TypedQuery<Realisateur> tp = this.em.createQuery("SELECT r FROM Realisateur r LEFT JOIN r.genres g WHERE g = :genre", Realisateur.class);
            tp.setParameter("genre", genre);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
