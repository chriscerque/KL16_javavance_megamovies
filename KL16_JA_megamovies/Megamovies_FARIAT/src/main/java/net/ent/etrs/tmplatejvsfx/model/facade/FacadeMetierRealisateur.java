package net.ent.etrs.tmplatejvsfx.model.facade;


import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;

import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
