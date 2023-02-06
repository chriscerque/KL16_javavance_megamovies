package net.ent.etrs.templatefx.model.facades;

import net.ent.etrs.templatefx.model.daos.RealisateurDao;
import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.daos.impl.DaosFactory;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.ConstantesModel;
import net.ent.etrs.templatefx.model.entities.references.Genre;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierRealisateurImpl implements FacadeMetierRealisateur {

    private static Log logJournal = LogFactory.getLog("LoggerInit2");

    private final RealisateurDao realisateurDao;

    public FacadeMetierRealisateurImpl() {
        this.realisateurDao = DaosFactory.getRealisateurDao();
    }

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
        try {
            logJournal.info(ConstantesModel.RECHERCHE_ALL_BDD_REALISATEUR);
            return IterableUtils.toList(this.realisateurDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Realisateur save(final Realisateur realisateur) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.SAUVEGARDE_BDD_REALISATEUR, realisateur));
            return this.realisateurDao.save(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Realisateur realisateur) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.SUPPRESION_BDD_REALISATEUR, realisateur));
            this.realisateurDao.delete(realisateur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.RECHERCHE_BDD_REALISATEUR_BY_GENRE, genre));
            return this.realisateurDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
