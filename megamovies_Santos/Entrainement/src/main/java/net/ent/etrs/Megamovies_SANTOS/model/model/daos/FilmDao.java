package net.ent.etrs.Megamovies_SANTOS.model.model.daos;

import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Film;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface FilmDao extends BaseDao<Film,Long> {




    List<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;


}
