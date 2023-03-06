package net.ent.etrs.projectname.model.dao.impl;

import net.ent.etrs.projectname.model.dao.FilmDao;
import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.Realisateur;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.*;

public class FilmDaoImpl extends AbstractJpaDao<Film, Long> implements FilmDao {

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) {
        Set<Film> setFilms = new HashSet<>();
        try {
            TypedQuery<Film> query = super.getEm()
                    .createQuery(" SELECT f FROM Film f WHERE  f.realisateur = :realisateur", Film.class);
            query.setParameter("realisateur", realisateur);
            setFilms = (Set<Film>) query.getResultList();
        } catch (NoResultException e) {
            setFilms = null;
        }
        return setFilms;
    }
}

