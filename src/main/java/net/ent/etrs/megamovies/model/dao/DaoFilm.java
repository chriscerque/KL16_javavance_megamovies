package net.ent.etrs.megamovies.model.dao;


import net.ent.etrs.megamovies.model.dao.base.BaseDao;
import net.ent.etrs.megamovies.model.entities.Film;

import java.io.Serializable;
import java.util.Optional;

public interface DaoFilm extends BaseDao<Film, Serializable> {

    Optional<Film> searchFilmByTitre(String nom);

}
