package net.ent.etrs.tmplatejvsfx.model.dao;

import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;

import java.io.Serializable;
import java.util.Set;

public interface DaoRealisateur extends BaseDao<Realisateur, Serializable>{

    Set<Realisateur> findByGenre(final Genre genre) throws DaoException;

}
