package net.ent.etrs.megamovie.model.daos;

import net.ent.etrs.megamovie.model.daos.base.BaseDao;
import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface FilmDao  extends BaseDao<Film, Serializable> {
    Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException;
}
