package net.ent.etrs.megamovie.model.daos.impl;


import net.ent.etrs.megamovie.model.daos.RealisateurDao;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RealisateurDaoImpl extends  AbstractDao<Realisateur, Long>  implements RealisateurDao {
    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT r FROM Realisateur r WHERE  :genre IN (r.genres)", Realisateur.class)
                            .setParameter("genre", genre)
                            .getResultStream().collect(Collectors.toSet())
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
