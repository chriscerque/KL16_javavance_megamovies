package net.ent.etrs.tmplatejvsfx.model.facade;


import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.entities.Film;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;

import java.util.Set;

public interface FacadeMetierFilm {
    Set<Film> findAll() throws BusinessException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

    void delete(Film film) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
