package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface FacadeMetierRealisateur extends BaseFacade<Realisateur, Serializable> {
    void initialisationRealisateur() throws BusinessException;
    Optional<Realisateur> findByNom(String nom) throws BusinessException;
    Set<Realisateur> findByGenre(Genre genre) throws BusinessException;
}
