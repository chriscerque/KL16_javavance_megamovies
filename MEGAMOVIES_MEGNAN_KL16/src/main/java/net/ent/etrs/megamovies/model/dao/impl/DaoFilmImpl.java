package net.ent.etrs.megamovies.model.dao.impl;

import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.dao.DaoFilm;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.Realisateur;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DaoFilmImpl extends AbstractJpaDao<Film, Serializable> implements DaoFilm {

    public static DaoFilmImpl dao;

    protected static DaoFilmImpl getInstance() {
        if (Objects.isNull(DaoFilmImpl.dao)) {
            DaoFilmImpl.dao = new DaoFilmImpl();
        }
        return DaoFilmImpl.dao;
    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException {
        try {
            TypedQuery<Film> tp = this.em.createQuery("SELECT a FROM Film a WHERE a.realisateur = :realisateur", Film.class);
            tp.setParameter("realisateur", realisateur);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
