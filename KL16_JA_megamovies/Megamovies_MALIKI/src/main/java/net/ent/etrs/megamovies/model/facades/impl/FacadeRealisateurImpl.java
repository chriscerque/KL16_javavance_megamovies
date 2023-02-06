package net.ent.etrs.megamovies.model.facades.impl;

import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.daos.references.DaoException;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.FacadeRealisateur;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.HashSet;
import java.util.Set;

public class FacadeRealisateurImpl implements FacadeRealisateur {
	
	private final RealisateurDao realisateurDao = DaosFactory.fabriquerDaoRealisateur();
	
	@Override
	public Set<Realisateur> readAll() throws BusinessException {
		try {
			return new HashSet<>(IterableUtils.toList(realisateurDao.findAll()));
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public Realisateur save(Realisateur realisateur) throws BusinessException {
		try {
			return this.realisateurDao.save(realisateur).get();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public void delete(Realisateur realisateur) throws BusinessException {
		try {
			this.realisateurDao.delete(realisateur.getId());
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@Override
	public Set<Realisateur> findByGenre(Genre genre) throws BusinessException {
		try {
			return new HashSet<>(IterableUtils.toList(realisateurDao.findByGenre(genre)));
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}
