package net.ent.etrs.megamovie.model.facades.impl;

import net.ent.etrs.megamovie.model.dao.FilmDao;
import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovie.model.entities.Film;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.AbstractBaseFM;
import net.ent.etrs.megamovie.model.facades.FacadeMetierFilm;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeFilmImpl extends AbstractBaseFM<Film> implements FacadeMetierFilm {

    private FilmDao dao;


    protected FacadeFilmImpl() {
        super(DaoFactory.getFilmDao());
        this.dao = (FilmDao) super.getDao();
    }

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException {
        try {
            return IterableUtils.toList(this.dao.findFilmsByRealisateur(realisateur)).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
