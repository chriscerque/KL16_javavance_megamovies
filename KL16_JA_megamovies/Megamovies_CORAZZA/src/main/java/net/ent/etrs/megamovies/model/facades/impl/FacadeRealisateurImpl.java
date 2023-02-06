package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;

import java.util.Set;

public class FacadeRealisateurImpl implements FacadeMetierRealisateur {
    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        return null;
    }

    @Override
    public Realisateur save(Realisateur realisateur) throws BusinessException {
        return null;
    }

    @Override
    public void delete(Realisateur realisateur) throws BusinessException {

    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        return null;
    }
}
