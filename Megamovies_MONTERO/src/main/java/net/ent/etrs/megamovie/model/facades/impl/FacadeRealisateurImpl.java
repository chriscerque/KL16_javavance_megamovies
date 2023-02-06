package net.ent.etrs.megamovie.model.facades.impl;

import net.ent.etrs.megamovie.model.dao.RealisateurDao;
import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovie.model.entities.Realisateur;
import net.ent.etrs.megamovie.model.entities.references.Genre;
import net.ent.etrs.megamovie.model.facades.AbstractBaseFM;
import net.ent.etrs.megamovie.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.util.Set;

public class FacadeRealisateurImpl extends AbstractBaseFM<Realisateur> implements FacadeMetierRealisateur {

    private RealisateurDao dao;


    protected FacadeRealisateurImpl() {
        super(DaoFactory.getRealisateurDao());
        this.dao = (RealisateurDao) super.getDao();
    }


    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws BusinessException {
        try {
            return this.dao.findRealisateurByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
