package net.ent.etrs.template.model.dao;

import net.ent.etrs.template.model.dao.commons.BaseDao;
import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;

import java.io.Serializable;
import java.util.List;

public interface DaoFilm extends BaseDao<Film, Serializable> {

    List<Film> findByRealisateur(final Realisateur realisateur);

}
