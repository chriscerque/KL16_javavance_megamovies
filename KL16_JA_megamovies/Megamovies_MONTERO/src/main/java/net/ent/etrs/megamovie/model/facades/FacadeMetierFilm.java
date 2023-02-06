package net.ent.etrs.megamovie.model.facades;


import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface FacadeMetierFilm extends IBaseFacadeMetier<Film, Serializable> {

    Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;

}
