package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierFilm {
    Set<Film> findAll() throws BusinessException;

    void delete(Film film) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
