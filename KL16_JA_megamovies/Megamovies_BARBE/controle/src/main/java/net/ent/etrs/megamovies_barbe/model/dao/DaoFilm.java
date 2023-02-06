package net.ent.etrs.megamovies_barbe.model.dao;


import net.ent.etrs.megamovies_barbe.model.dao.base.BaseDao;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface DaoFilm extends BaseDao<Film, Serializable> {


    Set<Film> findByRealisateur(Realisateur realisateur) throws DaoException;
}
