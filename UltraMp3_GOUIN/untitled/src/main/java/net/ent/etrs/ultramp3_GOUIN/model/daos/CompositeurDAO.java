package net.ent.etrs.ultramp3_GOUIN.model.daos;

import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface CompositeurDAO extends BaseDao<Compositeur, Serializable> {
    Set<Compositeur> findByGenre(final Genre genre) throws DaoException;
}
