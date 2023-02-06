package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.references.BusinessException;

import java.util.Set;

public interface FacadeFilm {
	Set<Film> findAll() throws BusinessException;
	
	Set<Film> findByRealisateur(final Realisateur realisateur) throws BusinessException;
	
	void delete(Film film) throws BusinessException;
	
	Film save(Film film) throws BusinessException;
}
