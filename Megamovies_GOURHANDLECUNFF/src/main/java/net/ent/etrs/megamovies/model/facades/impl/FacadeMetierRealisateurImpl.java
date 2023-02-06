package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IteratorUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class FacadeMetierRealisateurImpl extends AbstractFacade<Realisateur, Serializable> implements FacadeMetierRealisateur {
    private RealisateurDao dao;

    public FacadeMetierRealisateurImpl() {
        this.dao = DaosFactory.fabriquerRealisateurDao();
    }

    /**
     * @throws BusinessException
     */
    @Override
    public void initialisationRealisateur() throws BusinessException {

    }

    /**
     * @param nom
     * @return
     * @throws BusinessException
     */

    @Override
    public Optional<Realisateur> findByNom(String nom) throws BusinessException {
        try {
            return this.dao.findByNom(nom);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @param genre
     * @return
     * @throws BusinessException
     */
    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        try {
            return Set.copyOf(IteratorUtils.toList(this.dao.findByGenre(genre).iterator()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
