package net.ent.etrs.tmplatejvsfx.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.tmplatejvsfx.model.facade.FacadeMetierFilm;
import net.ent.etrs.tmplatejvsfx.model.facade.FacadeMetierRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    private final static FacadeMetierFilm facadeFilm;
    private final static FacadeMetierRealisateur facadeRealisateur;


    static {
        facadeFilm = new FacadeFilmImpl();
        facadeRealisateur = new FacadeRealisateurImpl();

    }




    public static FacadeMetierFilm fabriquerFacadeFilm() {
        return facadeFilm;
    }

    public static FacadeMetierRealisateur fabriquerFacadeRealisateur() {
        return facadeRealisateur;
    }

}

