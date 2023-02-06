package net.ent.etrs.megamovies_pelloquet.model.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_pelloquet.model.dao.impl.FilmDaoImpl;
import net.ent.etrs.megamovies_pelloquet.model.dao.impl.RealisateurDaoImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    public static RealisateurDao fabriquerDaoRealisateur() {
        return new RealisateurDaoImpl();
    }
    public static FilmDao fabriquerDaoFilm() {
        return new FilmDaoImpl();
    }


}
