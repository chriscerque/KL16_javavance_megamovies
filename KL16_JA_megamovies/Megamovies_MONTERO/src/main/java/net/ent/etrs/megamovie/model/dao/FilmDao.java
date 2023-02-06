package net.ent.etrs.megamovie.model.dao;


import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;

import java.io.Serializable;

public interface FilmDao extends BaseDao<Film, Serializable> {

    Iterable<Film> findFilmsByRealisateur(final Realisateur realisateur) throws DaoException;
}