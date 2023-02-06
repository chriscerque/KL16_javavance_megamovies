package net.ent.etrs.templatefx.model.facades;

import net.ent.etrs.templatefx.model.daos.FilmDao;
import net.ent.etrs.templatefx.model.daos.exception.DaoException;
import net.ent.etrs.templatefx.model.daos.impl.DaosFactory;
import net.ent.etrs.templatefx.model.entities.Film;
import net.ent.etrs.templatefx.model.entities.Realisateur;
import net.ent.etrs.templatefx.model.entities.references.ConstantesModel;
import net.ent.etrs.templatefx.model.facades.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {

    private static Log logJournal = LogFactory.getLog("LoggerInit2");
    private final FilmDao filmDao;

    public FacadeMetierFilmImpl() {
        this.filmDao = DaosFactory.getFilmDao();
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            logJournal.info(ConstantesModel.RECHERCHE_ALL_BDD_FILM);
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.RECHERCHE_BDD_FILM_BY_REALISATEUR, realisateur));
            return this.filmDao.findByRealisateur(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Film film) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.SUPPRESION_BDD_FILM, film));
            this.filmDao.delete(film.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Film save(final Film film) throws BusinessException {
        try {
            logJournal.info(String.format(ConstantesModel.SAUVEGARDE_BDD_FILM, film));
            return this.filmDao.save(film);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
