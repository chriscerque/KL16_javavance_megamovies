package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface FacadeFilm extends BaseFacade<Film, Serializable> {

    Set<Film> findAllByRealisateur(final Realisateur realisateur) throws BusinessException;

}
