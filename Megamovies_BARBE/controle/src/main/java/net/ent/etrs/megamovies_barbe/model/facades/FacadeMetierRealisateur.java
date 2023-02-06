package net.ent.etrs.megamovies_barbe.model.facades;


import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface FacadeMetierRealisateur {


    Optional<Realisateur> save(Realisateur realisateur) throws BusinessException;

    void delete(Serializable id) throws BusinessException;

    Optional<Realisateur> findById(Serializable id) throws BusinessException;

    Set<Realisateur> readAll() throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;


    Optional<Realisateur> findByName(String s) throws BusinessException;
}
