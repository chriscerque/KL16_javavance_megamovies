package net.ent.etrs.template.model.facades.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.template.model.facades.FacadeMetierFilm;
import net.ent.etrs.template.model.facades.FacadeMetierRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    /**
     * Fabrique une nouvelle instance de FacadeFilm
     * @return
     */
    public static FacadeMetierFilm fabriquerFacadeFilm(){
        return new FacadeFilmImpl();
    }

    /**
     * Fabrique une nouvelle instance de FacadeRealisateur
     * @return
     */
    public static FacadeMetierRealisateur fabriquerFacadeRealisateur(){
        return new FacadeRealisateurImpl();
    }


}
