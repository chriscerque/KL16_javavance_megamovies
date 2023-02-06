package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.facades.FacadeMetierFilm;

import java.util.Set;

public class FacadeFilmImpl implements FacadeMetierFilm {
    @Override
    public Set<Film> findAll() throws BusinessException {
        return null;
    }

    @Override
    public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        return null;
    }

    @Override
    public void delete(Film film) throws BusinessException {

    }

    @Override
    public Film save(Film film) throws BusinessException {
        return null;
    }
}
