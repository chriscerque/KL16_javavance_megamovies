package net.ent.etrs.projectname.model.facade;

import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.entities.reference.Genre;
import net.ent.etrs.projectname.model.exceptions.BusinessException;

import java.util.Optional;
import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException;

    Optional<Realisateur> save(final Realisateur realisateur) throws BusinessException;

    void delete(final Long pId) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
