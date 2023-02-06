package net.ent.etrs.Megamovies_SANTOS.model.model.daos.impl;

import net.ent.etrs.Megamovies_SANTOS.model.model.daos.FilmDao;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Film;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.DaoException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FilmDaoImpl extends AbstractJpaDao<Film,Long>implements FilmDao {
    private static FilmDaoImpl dao;


    protected static FilmDao getInstance() {
        if (Objects.isNull(FilmDaoImpl.dao)) {
            FilmDaoImpl.dao = new FilmDaoImpl();
        }
        return FilmDaoImpl.dao;
    }

    @Override
    public List<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
        return this.em.createQuery("SELECT f FROM Film  f WHERE f.realisateur = :realisateur", Film.class)
                .setParameter("realisateur", realisateur)
                .getResultList();
    }



}
