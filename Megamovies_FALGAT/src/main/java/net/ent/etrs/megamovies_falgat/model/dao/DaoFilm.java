package net.ent.etrs.megamovies_falgat.model.dao;

import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface DaoFilm extends BaseDao<Film, Serializable> {
    List<Film> readAllByGenre(Genre genre) throws DaoException;
}
