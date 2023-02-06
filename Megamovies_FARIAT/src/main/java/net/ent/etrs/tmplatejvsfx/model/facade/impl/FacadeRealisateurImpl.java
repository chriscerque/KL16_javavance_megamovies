package net.ent.etrs.tmplatejvsfx.model.facade.impl;

import net.ent.etrs.tmplatejvsfx.model.dao.DaoRealisateur;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.BusinessException;
import net.ent.etrs.tmplatejvsfx.model.dao.exception.DaoException;
import net.ent.etrs.tmplatejvsfx.model.dao.impl.DaosFactory;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;
import net.ent.etrs.tmplatejvsfx.model.facade.FacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeRealisateurImpl implements FacadeMetierRealisateur {
    private DaoRealisateur realisateurDao;

    protected FacadeRealisateurImpl() {
        this.realisateurDao = DaosFactory.getRealisateurDao();
    }



    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.realisateurDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Realisateur save(final Realisateur realisateur) throws BusinessException {
        try {
            return realisateurDao.save(realisateur).get();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Realisateur realisateur) throws BusinessException {
        try {
            realisateurDao.delete(realisateur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws BusinessException {
        try {
            return realisateurDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
