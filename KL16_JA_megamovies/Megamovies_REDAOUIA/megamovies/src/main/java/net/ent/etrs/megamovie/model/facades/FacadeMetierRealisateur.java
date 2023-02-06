package net.ent.etrs.megamovie.model.facades;


import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierRealisateur {

    Set<Realisateur> readAll() throws BusinessException, DaoException;

    Realisateur save(final Realisateur realisateur) throws BusinessException, DaoException;

    void delete(final Realisateur realisateur) throws BusinessException, DaoException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException, DaoException;
}
