package net.ent.etrs.megamovies.model.daos;

import net.ent.etrs.megamovies.model.daos.base.BaseDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Optional;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {
    Optional<Realisateur> findByNom(String nom) throws DaoException;
    Iterable<Realisateur> findByGenre(Genre genre) throws DaoException;
}
