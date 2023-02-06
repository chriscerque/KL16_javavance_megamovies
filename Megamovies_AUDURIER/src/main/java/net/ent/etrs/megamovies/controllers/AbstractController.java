package net.ent.etrs.megamovies.controllers;


import net.ent.etrs.megamovies.model.facades.IFilmFm;
import net.ent.etrs.megamovies.model.facades.IRealisateurFm;
import net.ent.etrs.megamovies.model.facades.impl.FacadeMetierFactory;

public abstract class AbstractController {

    protected static final IFilmFm FACADE_METIER_FILM;
    protected static final IRealisateurFm FACADE_METIER_REALISATEUR;

    static {
        FACADE_METIER_FILM = FacadeMetierFactory.fabriquerFmFilm();
        FACADE_METIER_REALISATEUR = FacadeMetierFactory.fabriquerFmRealisateur();
    }

}
