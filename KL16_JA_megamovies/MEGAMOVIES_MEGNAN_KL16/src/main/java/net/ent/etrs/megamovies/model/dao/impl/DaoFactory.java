package net.ent.etrs.megamovies.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.dao.DaoFilm;
import net.ent.etrs.megamovies.model.dao.DaoRealisateur;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class DaoFactory {

    public static DaoFilm fabriquerDaoFilm() {
        return DaoFilmImpl.getInstance();
    }

    public static DaoRealisateur fabriquerDaoRealisateur() {
        return DaoRealisateurImpl.getInstance();
    }
}
