package net.ent.etrs.projectname.model.facade;

import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierFilm {
    Set<Film> findAll() throws BusinessException;

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

    void delete(Film film) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
