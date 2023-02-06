package net.ent.etrs.megamovies.model.dao.impl;

import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DaoRealisateurImpl extends AbstractJpaDao<Realisateur, Serializable> implements DaoRealisateur {

    public static DaoRealisateurImpl dao;

    protected static DaoRealisateurImpl getInstance() {
        if (Objects.isNull(DaoRealisateurImpl.dao)) {
            DaoRealisateurImpl.dao = new DaoRealisateurImpl();
        }
        return DaoRealisateurImpl.dao;
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            TypedQuery<Realisateur> tp = this.em.createQuery("SELECT a FROM Realisateur a WHERE a.genres = :genre", Realisateur.class);
            tp.setParameter("genre", genre);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
