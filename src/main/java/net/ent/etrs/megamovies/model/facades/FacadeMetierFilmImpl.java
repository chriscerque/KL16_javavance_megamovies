package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.dao.DaoFilm;
import net.ent.etrs.megamovies.model.dao.impl.DaoException;
import net.ent.etrs.megamovies.model.dao.impl.DaoFactory;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierFilmImpl implements FacadeMetierFilm {

    private static DaoFilm daoFilm;

    static {
        daoFilm = DaoFactory.fabriquerDaoFilm();
    }

    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(daoFilm.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Film film) throws BusinessException {
        try {
            daoFilm.delete(film.getId());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
            return daoFilm.save(film).get();
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
