package net.ent.etrs.template.model.dao.impl;

import net.ent.etrs.template.model.dao.DaoFilm;
import net.ent.etrs.template.model.dao.request.request;
import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;

import javax.persistence.TypedQuery;
import java.util.List;

public class DaoFilmImpl extends AbstractDao<Film, Long> implements DaoFilm {


    @Override
    public List<Film> findByRealisateur(Realisateur realisateur) {
        TypedQuery<Film> q = super.getEm().createQuery(request.FIND_BY_REALISATEUR, Film.class);
        q.setParameter("realisateur", realisateur);
        return q.getResultList();
    }

}

