package net.ent.etrs.megamovies.model.facades;


import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.BusinessException;

import java.io.Serializable;
import java.util.Set;

public interface IRealisateurFm extends IBaseFacadeMetier<Realisateur, Serializable> {
    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;

}
