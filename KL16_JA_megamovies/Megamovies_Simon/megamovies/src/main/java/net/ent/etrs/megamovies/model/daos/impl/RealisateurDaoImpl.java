package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur> implements RealisateurDao {

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws DaoException {
        try {
            TypedQuery<Realisateur> tp = this.em.createQuery("SELECT r FROM Realisateur r WHERE r.nom = :genre", Realisateur.class);
            tp.setParameter("genre", genre);
            return tp.getResultList().stream().collect(Collectors.toSet());
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
