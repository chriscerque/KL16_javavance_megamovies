package net.ent.etrs.projectname.model.facade.impl;

import net.ent.etrs.projectname.model.dao.exception.DaoException;
import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.entities.reference.Genre;
import net.ent.etrs.projectname.model.exceptions.BusinessException;
import net.ent.etrs.projectname.model.facade.FacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.Optional;
import java.util.Set;

public final class FacadeMetierRealisateurImpl extends AbstractFacade implements FacadeMetierRealisateur {


    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        return this.realisateurDao.readAll();
    }

    @Override
    public Optional<Realisateur> save(final Realisateur realisateur) throws BusinessException {
        try {
            return this.realisateurDao.save(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(final Long pId) throws BusinessException {
        try {
            this.realisateurDao.delete(pId);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        return this.realisateurDao.findByGenre(genre);
    }
}
