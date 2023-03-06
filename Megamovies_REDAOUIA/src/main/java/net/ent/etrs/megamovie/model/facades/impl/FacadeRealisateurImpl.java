package net.ent.etrs.megamovie.model.facades.impl;

import net.ent.etrs.megamovie.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.Genre;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.util.HashSet;
import java.util.Set;

public class FacadeRealisateurImpl extends AbstractFacade implements FacadeMetierRealisateur {
    @Override
    public Set<Realisateur> readAll() throws BusinessException, DaoException {
        Set<Realisateur> realisateurs = new HashSet<>();
        for (Realisateur f: this.realisateurDao.findAll()) {
            realisateurs.add(f);
        };
        return realisateurs;
    }

    @Override
    public Realisateur save(Realisateur realisateur) throws BusinessException, DaoException {
        return this.realisateurDao.save(realisateur).orElse(null);
    }

    @Override
    public void delete(Realisateur realisateur) throws BusinessException, DaoException {
        this.realisateurDao.delete(realisateur);
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException, DaoException {
        return this.realisateurDao.findByGenre(genre);
    }
}
