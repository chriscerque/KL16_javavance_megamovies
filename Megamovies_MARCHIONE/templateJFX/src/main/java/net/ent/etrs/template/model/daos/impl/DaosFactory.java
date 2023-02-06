package net.ent.etrs.template.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.template.model.daos.Film;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {

    public static Film fabriquerFilmDao() {
        return (Film) FilmDaoImpl.getInstance();
    }



}
