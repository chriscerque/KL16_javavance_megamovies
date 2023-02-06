package net.ent.etrs.megamovies_barbe.model.facades;


import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface FacadeMetierFilm {


    Optional<Film> save(Film film) throws BusinessException;

    void delete(Serializable id) throws BusinessException;

    Optional<Film> findById(Serializable id) throws BusinessException;

    Set<Film> findAll() throws BusinessException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException, DaoException;


}
