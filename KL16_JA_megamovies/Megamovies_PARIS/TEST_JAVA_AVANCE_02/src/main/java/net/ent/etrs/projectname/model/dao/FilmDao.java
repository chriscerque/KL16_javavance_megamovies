package net.ent.etrs.projectname.model.dao;

import net.ent.etrs.projectname.model.dao.impl.FilmDaoImpl;
import net.ent.etrs.projectname.model.entities.Film;
import net.ent.etrs.projectname.model.entities.Realisateur;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface FilmDao extends BaseDao<Film, Long>{


    Set<Film> findByRealisateur(Realisateur realisateur);


}
