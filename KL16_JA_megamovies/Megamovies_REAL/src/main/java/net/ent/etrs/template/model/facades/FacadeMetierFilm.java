package net.ent.etrs.template.model.facades;



import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface FacadeMetierFilm {

    Set<Film> findAll() throws BusinessException;

    List<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

    void delete(Serializable id) throws BusinessException;

    Film save(Film film) throws BusinessException;
}
