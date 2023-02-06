package net.ent.etrs.projectname.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projectname.model.dao.FilmDao;
import net.ent.etrs.projectname.model.dao.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {

    
    public static FilmDao fabriquerFilmDao(){
        return new FilmDaoImpl();
    }

    public static RealisateurDao fabriquerRealisateurDao(){
        return new RealisateurDaoImpl();
    }



}
