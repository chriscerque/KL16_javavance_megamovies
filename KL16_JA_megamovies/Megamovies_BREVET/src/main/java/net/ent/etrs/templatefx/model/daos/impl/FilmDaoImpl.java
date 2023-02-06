package net.ent.etrs.templatefx.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.templatefx.model.daos.FilmDao;
import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.Realisateur;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilmDaoImpl extends AbstractJpaDao<Film, Serializable> implements FilmDao {
    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException {
        try {
            TypedQuery<Film> tp = this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :realisateur", Film.class);
            tp.setParameter("realisateur", realisateur);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
