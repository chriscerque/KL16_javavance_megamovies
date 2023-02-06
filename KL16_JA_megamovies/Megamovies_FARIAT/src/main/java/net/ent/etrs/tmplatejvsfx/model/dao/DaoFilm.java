package net.ent.etrs.tmplatejvsfx.model.dao;

import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface DaoFilm extends BaseDao <Film, Serializable>{



    Set<Film> findByRealisateur(final Realisateur realisateur) throws DaoException;



}
