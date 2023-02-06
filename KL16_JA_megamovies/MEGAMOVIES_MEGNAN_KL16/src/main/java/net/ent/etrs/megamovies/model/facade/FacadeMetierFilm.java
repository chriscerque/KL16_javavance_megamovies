package net.ent.etrs.megamovies.model.facade;

import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.model.entity.Film;
import net.ent.etrs.megamovies.model.entity.Realisateur;

import java.util.List;
import java.util.Set;

public interface FacadeMetierFilm {
    List<Film> findAll() throws BusinessException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

    void delete(Film film) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
