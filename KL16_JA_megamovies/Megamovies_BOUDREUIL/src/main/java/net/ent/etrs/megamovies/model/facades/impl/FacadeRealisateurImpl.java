package net.ent.etrs.megamovies.model.facades.impl;


import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.FacadeRealisateur;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.util.Set;

public class FacadeRealisateurImpl extends AbstractBaseFacade<Realisateur> implements FacadeRealisateur {

    private static RealisateurDao dao;

    public FacadeRealisateurImpl() {
        super(DaoFactory.fabriquerRealisateurDao());
        dao = (RealisateurDao) super.getDao();
    }

    @Override
    public Set<Realisateur> findAllByGenre(final Genre genre) throws BusinessException {
        try {
            return dao.searchRealisateurByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}