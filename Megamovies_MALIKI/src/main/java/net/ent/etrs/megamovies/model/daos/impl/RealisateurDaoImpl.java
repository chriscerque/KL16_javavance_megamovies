package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RealisateurDaoImpl extends AbstractDao<Realisateur, Serializable> implements RealisateurDao {
	@Override
	public Set<Realisateur> findByGenre(Genre genre) throws BusinessException, DaoException {
		try {
			return new HashSet<>(
					this.em.createQuery("SELECT r FROM Realisateur r WHERE r.genres = :genre", Realisateur.class).setParameter("genre", genre).getResultList());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
