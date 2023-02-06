package net.ent.etrs.megamovies.model.facades;


import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface FacadeRealisateur extends BaseFacade<Realisateur, Serializable> {

    Set<Realisateur> findAllByGenre(final Genre genre) throws BusinessException;

}
