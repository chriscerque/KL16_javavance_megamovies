package net.ent.etrs.megamovies.model.daos;

import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface FilmDao extends BaseDao<Film, Serializable>{

    Set<Film> searchFilmByReal(Realisateur realisateur) throws DaoException;
}
