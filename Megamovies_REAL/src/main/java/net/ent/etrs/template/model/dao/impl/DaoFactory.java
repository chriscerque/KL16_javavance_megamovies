package net.ent.etrs.template.model.dao.impl;

import net.ent.etrs.template.model.dao.DaoFilm;
import net.ent.etrs.template.model.dao.DaoRealisateur;

public class DaoFactory {

    public static DaoRealisateur fabriquerRealisateur() {
        return new DaoRealisateurImpl();
    }

    public static DaoFilm fabriquerFilm() {
        return new DaoFilmImpl();
    }
}
