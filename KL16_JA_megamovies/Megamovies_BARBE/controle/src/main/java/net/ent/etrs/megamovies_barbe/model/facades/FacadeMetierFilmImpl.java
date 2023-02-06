package net.ent.etrs.megamovies_barbe.model.facades;

import net.ent.etrs.megamovies_barbe.model.dao.DaoFilm;
import net.ent.etrs.megamovies_barbe.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovies_barbe.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies_barbe.model.entity.Film;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {
    private static Log log = LogFactory.getLog("LoggerInit");
    private DaoFilm filmDao;

    public FacadeMetierFilmImpl() {
        this.filmDao = DaoFactory.fabriquerDaoFilmJpaFactory();
    }

    @Override
    public Optional<Film> save(Film film) throws BusinessException {
        try {
            log.info("film + " + film + " correctement enregistré");
            return this.filmDao.save(film);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public void delete(Serializable id) throws BusinessException {
        try {
            filmDao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Film> findById(Serializable id) throws BusinessException {
        try {
            return this.filmDao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.filmDao.findAll()).stream().collect(Collectors.toSet());

        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e.getCause());
        }
    }

    @Override public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException, DaoException {
        return this.filmDao.findByRealisateur(realisateur);
    }

}
