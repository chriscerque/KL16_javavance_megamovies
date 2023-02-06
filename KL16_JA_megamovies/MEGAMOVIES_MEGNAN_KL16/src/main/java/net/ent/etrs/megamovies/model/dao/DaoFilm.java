package net.ent.etrs.megamovies.model.dao;

import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface DaoFilm extends BaseDao<Film, Serializable> {
    Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException;
}
