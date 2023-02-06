package net.ent.etrs.megamovies.model.daos.impl;



import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {


    public static FilmDao getFilmDao(){
        return new FilmDaoImpl();
    }

    public static RealisateurDao getRealisateurDao(){
        return new RealisateurDaoImpl();
    }
}
