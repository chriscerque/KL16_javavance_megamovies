package net.ent.etrs.megamovies_pelloquet.model.dao;




import net.ent.etrs.megamovies_pelloquet.model.dao.base.BaseDao;
import net.ent.etrs.megamovies_pelloquet.model.entities.Film;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;

import java.io.Serializable;
import java.util.Set;

public interface FilmDao extends BaseDao<Film, Serializable> {

    Set<Film> findByRealisateur(Realisateur realisateur);
}
