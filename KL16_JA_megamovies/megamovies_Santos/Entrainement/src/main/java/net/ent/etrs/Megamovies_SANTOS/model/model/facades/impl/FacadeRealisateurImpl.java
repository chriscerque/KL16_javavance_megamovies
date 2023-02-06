package net.ent.etrs.Megamovies_SANTOS.model.model.facades.impl;

import net.ent.etrs.Megamovies_SANTOS.model.model.daos.RealisateurDao;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.DaoException;
import net.ent.etrs.Megamovies_SANTOS.model.model.facades.FacadeMetierRealisateur;
import org.apache.commons.collections.IteratorUtils;

import java.util.List;
import java.util.Set;

public class FacadeRealisateurImpl implements FacadeMetierRealisateur {


    private RealisateurDao dao;



    @Override
    public List readAll() throws BusinessException {
        try {
            return IteratorUtils.toList(this.dao.findAll().iterator());
        }
        catch (Exception e) {
            throw new BusinessException(e.getMessage());
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
            this.dao.delete(realisateur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e);

    }

    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        try {
            return this.findByGenre(genre);
        }catch (Exception e)
            {
                throw new BusinessException(e.getMessage());
            }
    }

    }

