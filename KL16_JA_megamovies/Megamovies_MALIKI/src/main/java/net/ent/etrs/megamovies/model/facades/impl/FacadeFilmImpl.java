package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.FacadeFilm;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeFilmImpl implements FacadeFilm {
	
	private final FilmDao filmDao = DaosFactory.fabriquerDaoFilm();
	
	
	@Override
	public Set<Film> findAll() throws BusinessException {
		try {
			return new HashSet<>(IterableUtils.toList(filmDao.findAll()));
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException {
		try {
			return filmDao.findByRealisateur(realisateur);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public void delete(Film film) throws BusinessException {
		try {
			this.filmDao.delete(film.getId());
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public Film save(Film film) throws BusinessException {
		try {
			return this.filmDao.save(film).get();
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}
	}
}
