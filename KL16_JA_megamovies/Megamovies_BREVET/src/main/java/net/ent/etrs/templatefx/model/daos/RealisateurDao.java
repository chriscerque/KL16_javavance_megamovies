package net.ent.etrs.templatefx.model.daos;

import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {
    Set<Realisateur> findByGenre(final Genre genre) throws DaoException;
}
