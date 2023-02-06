package net.ent.etrs.megamovies_falgat.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeFilm;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {

    public static FacadeRealisateur fabriquerFacadeRealisateur(){
        return new FacadeRealisateurImpl();
    }

    public static FacadeFilm fabriquerFacadeFilm(){
        return new FacadeFilmImpl();
    }



}
