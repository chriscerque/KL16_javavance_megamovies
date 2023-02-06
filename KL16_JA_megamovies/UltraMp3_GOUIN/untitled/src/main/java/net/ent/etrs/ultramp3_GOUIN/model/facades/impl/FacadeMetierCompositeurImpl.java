package net.ent.etrs.ultramp3_GOUIN.model.facades.impl;

import net.ent.etrs.ultramp3_GOUIN.model.daos.CompositeurDAO;
import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.daos.impl.DaosFactory;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierCompositeur;
import net.ent.etrs.ultramp3_GOUIN.model.facades.exceptions.BusinessException;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FacadeMetierCompositeurImpl implements FacadeMetierCompositeur {

    private CompositeurDAO dao;

    public FacadeMetierCompositeurImpl() {
        this.dao = DaosFactory.fabriquerCompositeurDAO();
    }

    @Override
    public Set<Compositeur> readAll() throws BusinessException {
        try {
            return StreamSupport.stream(this.dao.findAll().spliterator(), false).collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Compositeur save(Compositeur compositeur) throws BusinessException {
        try {
            return this.dao.save(compositeur).orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Compositeur compositeur) throws BusinessException {
        try {
            this.dao.delete(compositeur.getId());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<Compositeur> findByGenre(Genre genre) throws BusinessException {
        try {
            return this.dao.findByGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
