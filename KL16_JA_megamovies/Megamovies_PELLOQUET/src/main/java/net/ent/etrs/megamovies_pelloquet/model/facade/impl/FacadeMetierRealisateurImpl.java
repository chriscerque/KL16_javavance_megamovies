package net.ent.etrs.megamovies_pelloquet.model.facade.impl;

import net.ent.etrs.megamovies_pelloquet.model.dao.DaoException;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;
import net.ent.etrs.megamovies_pelloquet.model.facade.AbstractFacade;
import net.ent.etrs.megamovies_pelloquet.model.facade.Exceptions.BusinessException;
import net.ent.etrs.megamovies_pelloquet.model.facade.FacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierRealisateurImpl extends AbstractFacade<Realisateur> implements FacadeMetierRealisateur {

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.realisateurDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Realisateur save(Realisateur realisateur) throws BusinessException {
        try {
            return this.realisateurDao.save(realisateur).get();
        } catch (NoSuchElementException | DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Realisateur realisateur) throws BusinessException {
        try {
            this.realisateurDao.delete(realisateur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        return this.realisateurDao.searchRealisateurByGenre(genre);
    }
}
