package net.ent.etrs.megamovies_pelloquet.model.dao.impl;

import net.ent.etrs.megamovies_pelloquet.model.dao.AbstractDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.FilmDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.references.ConstantesDao;
import net.ent.etrs.megamovies_pelloquet.model.entities.Film;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDaoImpl extends AbstractDao<Film, Serializable> implements FilmDao {
    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) {
        List<Film> lst = new ArrayList<>();
        try {
            TypedQuery<Film> query = super.getEm().createQuery(ConstantesDao.QUERY_FILM_PAR_REALISATEUR, Film.class);
            query.setParameter("realisateur", realisateur);
            lst = query.getResultList();
        } catch (NoResultException e) {
            lst = null;
        }
        return lst.stream().collect(Collectors.toSet());
    }
}
