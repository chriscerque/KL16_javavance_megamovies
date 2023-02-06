package net.ent.etrs.megamovie.model.facades;


import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Genre;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface FacadeMetierRealisateur extends IBaseFacadeMetier<Realisateur, Serializable> {

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;

}
