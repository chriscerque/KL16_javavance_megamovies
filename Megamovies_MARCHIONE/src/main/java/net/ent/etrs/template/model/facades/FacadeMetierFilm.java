package net.ent.etrs.template.model.facades;

import net.ent.etrs.template.model.daos.Film;
import net.ent.etrs.template.model.entities.references.Realisateur;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierFilm {
    Set<Film> findAll() throws BusinessException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

    void delete(Film film) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
