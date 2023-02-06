package net.ent.etrs.Megamovies_SANTOS.model.model.daos;

import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.DaoException;

import java.util.List;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur , Long> {

    Set<Realisateur> readAll() throws BusinessException, DaoException;

//    Realisateur save(final Realisateur realisateur);

//    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException, DaoException;
}
