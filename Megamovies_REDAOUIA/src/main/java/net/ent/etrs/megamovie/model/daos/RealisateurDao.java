package net.ent.etrs.megamovie.model.daos;

import net.ent.etrs.megamovie.model.daos.base.BaseDao;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao  extends BaseDao<Realisateur, Serializable> {
    Set<Realisateur> findByGenre(Genre genre) throws DaoException;
}
