package net.ent.etrs.megamovies_falgat.model.facades.impl;

import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;
import net.ent.etrs.megamovies_falgat.model.facades.FacadeRealisateur;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FacadeRealisateurImpl extends AbstractFacade<Realisateur> implements FacadeRealisateur {

    private static final Logger LOG = LogManager.getLogger("logBdd");
    @Override
    public List<Realisateur> readAllRealisateur() throws BusinessException {
        try {
            List<Realisateur> list = IteratorUtils.toList(super.daoRealisateur.findAll().iterator());
            LOG.info("Liste extraite avec succès de la bdd");
            return list;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Realisateur> readAllRealisateurByGenre(final Genre genre) throws BusinessException {
        try {
            List<Realisateur> list = super.daoRealisateur.readAllByGenre(genre);
            LOG.info("Liste extraite avec succès de la bdd");
            return list;
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
