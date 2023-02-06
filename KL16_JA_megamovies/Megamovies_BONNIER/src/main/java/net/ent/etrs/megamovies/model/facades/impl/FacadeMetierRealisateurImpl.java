package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.exceptions.DaoException;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import org.apache.commons.collections4.IteratorUtils;

import java.util.Set;

public class FacadeMetierRealisateurImpl implements FacadeMetierRealisateur {

    private RealisateurDao dao;

    public FacadeMetierRealisateurImpl() {
        this.dao = DaosFactory.fabriquerRealisateurDao();
    }

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            return (Set<Realisateur>) IteratorUtils.toList(this.dao.findAll().iterator());
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
