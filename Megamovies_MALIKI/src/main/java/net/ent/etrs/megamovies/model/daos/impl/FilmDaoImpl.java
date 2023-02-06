package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FilmDaoImpl extends AbstractDao<Film, Serializable> implements FilmDao {
	
	
	@Override
	public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException, DaoException {
		try {
			return new HashSet<>(
					this.em.createQuery("SELECT f FROM Film r WHERE r.realisateur = :real", Film.class).setParameter("real", realisateur).getResultList());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}


