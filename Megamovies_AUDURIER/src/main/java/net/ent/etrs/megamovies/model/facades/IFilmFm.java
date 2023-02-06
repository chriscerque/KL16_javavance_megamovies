package net.ent.etrs.megamovies.model.facades;


import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exception.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface IFilmFm extends IBaseFacadeMetier<Film, Serializable> {
    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;
}
