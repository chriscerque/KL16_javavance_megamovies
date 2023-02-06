package net.ent.etrs.megamovies_barbe.model.facades;


import net.ent.etrs.megamovies_barbe.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierRealisateurImpl implements FacadeMetierRealisateur {
    private static Log log = LogFactory.getLog("LoggerInit");
    private DaoRealisateur realisateurDao;

    public FacadeMetierRealisateurImpl() {
        this.realisateurDao = DaoFactory.fabriquerDaoRealisateurJpaFactory();
    }

    @Override
    public Optional<Realisateur> save(Realisateur realisateur) throws BusinessException {
        try {
            log.info("réalisateur + " + realisateur + " correctement enregistré");
            return this.realisateurDao.save(realisateur);
        } catch (DaoException e) {
            log.error(e.getCause(), e);
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public void delete(Serializable id) throws BusinessException {
        try {
            realisateurDao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Realisateur> findById(Serializable id) throws BusinessException {
        try {
            return this.realisateurDao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.realisateurDao.findAll()).stream().collect(Collectors.toSet());

        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
        try {
            return this.realisateurDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override public Optional<Realisateur> findByName(String s) throws BusinessException {
        try {
            return this.realisateurDao.findByName(s);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
