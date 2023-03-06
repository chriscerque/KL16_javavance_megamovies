package net.ent.etrs.template.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.template.model.daos.Film;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmDaoImpl extends AbstractJpaDao<net.ent.etrs.template.model.entities.Film, Long> implements Film {

    protected static FilmDaoImpl dao;



    protected static FilmDaoImpl getInstance() {
        if (Objects.isNull(FilmDaoImpl.dao)) {
            FilmDaoImpl.dao = new FilmDaoImpl();
        }
        return FilmDaoImpl.dao;
    }



}
