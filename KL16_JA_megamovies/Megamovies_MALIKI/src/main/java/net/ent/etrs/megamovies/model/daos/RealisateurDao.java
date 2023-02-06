package net.ent.etrs.megamovies.model.daos;

import net.ent.etrs.megamovies.model.daos.base.BaseDao;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {
	
	Set<Realisateur> findByGenre(final Genre genre) throws BusinessException, DaoException;
}
