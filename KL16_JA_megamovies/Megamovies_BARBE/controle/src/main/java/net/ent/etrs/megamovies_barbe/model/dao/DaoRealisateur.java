package net.ent.etrs.megamovies_barbe.model.dao;


import net.ent.etrs.megamovies_barbe.model.dao.base.BaseDao;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface DaoRealisateur extends BaseDao<Realisateur, Serializable> {


    Set<Realisateur> findByGenre(Genre genre) throws DaoException;

    Optional<Realisateur> findByName(String s) throws DaoException;
}
