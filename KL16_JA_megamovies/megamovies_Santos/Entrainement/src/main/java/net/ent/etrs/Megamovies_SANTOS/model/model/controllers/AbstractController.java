package net.ent.etrs.Megamovies_SANTOS.model.model.controllers;


import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierFilm;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.impl.FacadeFilmImpl;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.impl.FacadeRealisateurImpl;

public abstract class AbstractController {

    protected static final FacadeMetierFilm FACADE_FILM;
    protected static final FacadeMetierRealisateur FACADE_METIER_REALISATEUR;

    static {
        FACADE_FILM = new FacadeFilmImpl();

        FACADE_METIER_REALISATEUR = new FacadeRealisateurImpl();


    }
}
