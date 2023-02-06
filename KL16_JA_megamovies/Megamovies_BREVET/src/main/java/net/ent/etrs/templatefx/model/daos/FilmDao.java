package net.ent.etrs.templatefx.model.daos;

import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface FilmDao extends BaseDao<Film, Serializable> {

    Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException;
}
