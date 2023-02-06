package net.ent.etrs.megamovies.model.daos;

import net.ent.etrs.megamovies.model.daos.base.BaseDao;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface FilmDao extends BaseDao<Film, Serializable> {
	Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException, DaoException;
}
