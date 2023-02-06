package net.ent.etrs.megamovie.model.dao.impl;


import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Genre;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RealisateurDao extends AbstractJPADao<Realisateur> implements net.ent.etrs.megamovie.model.dao.RealisateurDao {

    @Override
    public Set<Realisateur> findRealisateurByGenre(final Genre genre) throws DaoException {
        try {
            TypedQuery<Realisateur> result = this.em.createQuery("SELECT realisateur FROM Realisateur realisateur left  join  realisateur.genres rg where UPPER(rg) IN( :genre )", Realisateur.class);

            Set<Genre> param = new HashSet<>();
            param.add(genre);
            result.setParameter("genre", param);


            return result.getResultList().stream().collect(Collectors.toSet());
        } catch (NoResultException e) {
            throw new DaoException(e);
        }
    }
}