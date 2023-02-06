package net.ent.etrs.megamovies.model.facade;

import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import java.util.List;
import java.util.Set;

public interface FacadeMetierRealisateur {

    List<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
