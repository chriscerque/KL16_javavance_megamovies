package net.ent.etrs.tmplatejvsfx.model.dao.impl;

import net.ent.etrs.tmplatejvsfx.model.dao.DaoFilm;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;

import java.io.Serializable;
import java.util.*;

public class DaoFilmImpl extends AbstractJpaDao<Film, Serializable> implements DaoFilm {

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException {
        return new HashSet<>(
                this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :realisateur", Film.class)
                        .setParameter("realisateur", realisateur)
                        .getResultList()
        );
    }


}
