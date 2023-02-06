package net.ent.etrs.megamovie.model.facades;


import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierFilm {
    Set<Film> findAll() throws BusinessException, DaoException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException, DaoException;

    void delete(Film film) throws BusinessException, DaoException;

    Film save(Film film) throws BusinessException, DaoException;
}
