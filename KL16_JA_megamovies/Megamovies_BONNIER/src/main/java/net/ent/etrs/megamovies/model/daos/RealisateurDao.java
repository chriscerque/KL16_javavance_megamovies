package net.ent.etrs.megamovies.model.daos;


import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {

    Set<Realisateur> findByGenre(final Genre genre) throws DaoException;
}
