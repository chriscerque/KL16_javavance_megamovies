package net.ent.etrs.megamovies.controller;

import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facade.impl.FacadeFactory;
import net.ent.etrs.megamovies.model.facade.impl.FacadeMetierFilmImpl;

public abstract class AbstractController {
    protected static final FacadeMetierFilm FACADE_FILM ;

    static {
        FACADE_FILM = new FacadeMetierFilmImpl();
    }

}
