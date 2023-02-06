package net.ent.etrs.megamovie.model.dao;


import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {


    Set<Realisateur> findRealisateurByGenre(final Genre genre) throws DaoException;
}