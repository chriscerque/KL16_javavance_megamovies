package net.ent.etrs.template.model.facades.impl;

import net.ent.etrs.template.model.dao.exceptions.DaoException;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.template.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FacadeRealisateurImpl extends AbstractFacade implements FacadeMetierRealisateur {

    @Override
    public Set<Realisateur> readAll() {
        try {
            return IterableUtils.toList(daoRealisateur.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Realisateur save(Realisateur realisateur) throws BusinessException {
        try {
            return this.daoRealisateur.save(realisateur).get();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Realisateur realisateur) throws BusinessException {
        try {
            this.daoRealisateur.delete(realisateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Realisateur> findByGenre(Genre genre) {
        return this.daoRealisateur.findByGenre(genre);
    }
}
