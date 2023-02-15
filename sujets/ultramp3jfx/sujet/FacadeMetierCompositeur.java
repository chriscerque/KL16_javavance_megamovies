package net.ent.etrs.ultramp3.model.facades;

import net.ent.etrs.ultramp3.model.entities.Compositeur;
import net.ent.etrs.ultramp3.model.entities.references.Genre;
import net.ent.etrs.ultramp3.model.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierCompositeur {

    Set<Compositeur> readAll() throws BusinessException;

    Compositeur save(final Compositeur compositeur) throws BusinessException;

    void delete(final Compositeur compositeur) throws BusinessException;

    Set<Compositeur> findByGenre(final Genre genre) throws BusinessException;
}
