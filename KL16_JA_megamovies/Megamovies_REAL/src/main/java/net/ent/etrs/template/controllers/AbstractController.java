package net.ent.etrs.template.controllers;


import net.ent.etrs.template.model.facades.FacadeMetierFilm;
import net.ent.etrs.template.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.template.model.facades.impl.FacadeFilmImpl;
import net.ent.etrs.template.model.facades.impl.FacadeRealisateurImpl;

public abstract class AbstractController {

    protected static final FacadeMetierFilm FACADE_FILM;
    protected static final FacadeMetierRealisateur FACADE_REALISATEUR;



    static {
        FACADE_FILM = new FacadeFilmImpl();
        FACADE_REALISATEUR = new FacadeRealisateurImpl();
    }
}
