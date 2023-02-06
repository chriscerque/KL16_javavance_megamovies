package net.ent.etrs.template.model.dao.impl;

import net.ent.etrs.template.model.dao.DaoRealisateur;
import net.ent.etrs.template.model.dao.request.request;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;

import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRealisateurImpl extends AbstractDao<Realisateur, Long> implements DaoRealisateur {

    @Override
    public List<Realisateur> findByGenre(Genre genre) {
        TypedQuery<Realisateur> q = super.getEm().createQuery(request.FIND_BY_GENRE, Realisateur.class);
        q.setParameter("genre", genre);
        return q.getResultList();
    }

}
