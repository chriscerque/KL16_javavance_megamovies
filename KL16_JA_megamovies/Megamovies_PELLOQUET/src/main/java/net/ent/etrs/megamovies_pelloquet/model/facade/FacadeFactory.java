package net.ent.etrs.megamovies_pelloquet.model.facade;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_pelloquet.model.facade.impl.FacadeMetierFilmImpl;
import net.ent.etrs.megamovies_pelloquet.model.facade.impl.FacadeMetierRealisateurImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

    /**
     * Fabrique une nouvelle instance de FacadeMetierFilm.
     *
     * @return une instance de FacadeMetierFilm
     */
    public static FacadeMetierFilm fabriquerFacadeFilm() {
        return new FacadeMetierFilmImpl();
    }

    /**
     * Fabrique une nouvelle instance de FacadeMetierRealisateur.
     *
     * @return une instance de FacadeMetierRealisateur
     */
    public static FacadeMetierRealisateur fabriquerFacadeRealisateur() {
        return new FacadeMetierRealisateurImpl();
    }

}
