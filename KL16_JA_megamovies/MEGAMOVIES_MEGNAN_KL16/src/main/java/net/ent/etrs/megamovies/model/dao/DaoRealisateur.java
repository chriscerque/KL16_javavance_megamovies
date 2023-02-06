package net.ent.etrs.megamovies.model.dao;

import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface DaoRealisateur extends BaseDao<Realisateur, Serializable> {

    Set<Realisateur> findByGenre(Genre genre) throws DaoException;
}
