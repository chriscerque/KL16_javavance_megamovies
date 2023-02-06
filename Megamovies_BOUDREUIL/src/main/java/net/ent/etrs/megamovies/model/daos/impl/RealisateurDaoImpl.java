package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.util.HashSet;
import java.util.Set;


public class RealisateurDaoImpl extends AbstractJPADao<Realisateur> implements RealisateurDao {


    @Override
    public Set<Realisateur> searchRealisateurByGenre(final Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT r FROM Realisateur r WHERE :genre IN(r.genres)", Realisateur.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}