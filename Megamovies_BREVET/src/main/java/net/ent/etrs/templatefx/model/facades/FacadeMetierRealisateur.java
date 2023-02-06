package net.ent.etrs.templatefx.model.facades;

import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.Genre;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;

import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
