package net.ent.etrs.megamovies_pelloquet.model.facade;



import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;
import net.ent.etrs.megamovies_pelloquet.model.facade.Exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
