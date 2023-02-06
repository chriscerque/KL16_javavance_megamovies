package net.ent.etrs.megamovies.model.facade.impl;

import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entity.Realisateur;
import net.ent.etrs.megamovies.model.entity.references.Genre;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Set;

public class FacadeMetierRealisateurImpl implements FacadeMetierRealisateur {

    private DaoRealisateur dao;

    public FacadeMetierRealisateurImpl() {
        this.dao = DaoFactory.fabriquerDaoRealisateur();
    }

    @Override
    public List<Realisateur> readAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.dao.findAll());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Realisateur save(Realisateur realisateur) throws BusinessException {
        try {
            return this.dao.save(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Realisateur realisateur) throws BusinessException {
        try {
            this.dao.delete(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        try {
            return this.dao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
