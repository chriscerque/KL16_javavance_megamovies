package net.ent.etrs.template.model.facades;



import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    List<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
