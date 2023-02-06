package net.ent.etrs.projectname.model.facade;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projectname.model.facade.impl.FacadeMetierFilmImpl;
import net.ent.etrs.projectname.model.facade.impl.FacadeMetierRealisateurImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    public static FacadeMetierFilm fabriquerFilmFacade() {
        return new FacadeMetierFilmImpl();
    }


    public static FacadeMetierRealisateur fabriquerRealisateurFacade() {
        return new FacadeMetierRealisateurImpl();
    }



}

