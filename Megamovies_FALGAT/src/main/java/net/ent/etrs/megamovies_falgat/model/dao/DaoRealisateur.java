package net.ent.etrs.megamovies_falgat.model.dao;

import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface DaoRealisateur extends BaseDao<Realisateur, Serializable> {
    List<Realisateur> readAllByGenre(Genre genre) throws DaoException;
}
