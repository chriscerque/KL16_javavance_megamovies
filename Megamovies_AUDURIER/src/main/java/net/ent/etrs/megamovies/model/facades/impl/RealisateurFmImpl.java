package net.ent.etrs.megamovies.model.facades.impl;


import net.ent.etrs.megamovies.model.daos.IRealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.BusinessException;
import net.ent.etrs.megamovies.model.facades.AbstractBaseFM;
import net.ent.etrs.megamovies.model.facades.IRealisateurFm;

import java.util.Set;

public class RealisateurFmImpl extends AbstractBaseFM<Realisateur> implements IRealisateurFm {
    private final IRealisateurDao dao;

    protected RealisateurFmImpl() {
        super(DaoFactory.getRealisateurDao());
        this.dao = (IRealisateurDao) super.getDao();
    }

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws BusinessException {
        try {
            return this.dao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}