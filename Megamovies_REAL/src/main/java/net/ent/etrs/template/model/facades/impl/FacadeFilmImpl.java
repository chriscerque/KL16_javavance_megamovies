package net.ent.etrs.template.model.facades.impl;

import net.ent.etrs.template.model.dao.exceptions.DaoException;
import net.ent.etrs.template.model.entities.Film;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.facades.FacadeMetierFilm;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeFilmImpl extends AbstractFacade implements FacadeMetierFilm {


    @Override
    public Set<Film> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.daoFilm.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    //TODO SI ERREUR DE RECHERCHE ALORS TRANSFORMER EN SET<Film>
    public List<Film> findByRealisateur(Realisateur realisateur) {
        return this.daoFilm.findByRealisateur(realisateur);
    }

    @Override
    public void delete(Serializable id) throws BusinessException {
        try {
            this.daoFilm.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Film save(Film film) throws BusinessException {
        try {
            return this.daoFilm.save(film).get();
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
