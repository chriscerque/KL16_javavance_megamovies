package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies.model.dao.impl.DaoException;
import net.ent.etrs.megamovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierRealisateurImpl implements FacadeMetierRealisateur {

    private static DaoRealisateur daoRealisateur;

    static {
        daoRealisateur = DaoFactory.fabriquerDaoRealisateur();
    }

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            return IterableUtils.toList(daoRealisateur.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
