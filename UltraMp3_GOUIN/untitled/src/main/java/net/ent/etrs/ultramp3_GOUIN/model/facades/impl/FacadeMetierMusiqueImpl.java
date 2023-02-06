package net.ent.etrs.ultramp3_GOUIN.model.facades.impl;

import net.ent.etrs.ultramp3_GOUIN.model.daos.MusiqueDAO;
import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.daos.impl.DaosFactory;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;
import net.ent.etrs.ultramp3_GOUIN.model.facades.FacadeMetierMusique;
import net.ent.etrs.ultramp3_GOUIN.model.facades.exceptions.BusinessException;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FacadeMetierMusiqueImpl implements FacadeMetierMusique {

    private MusiqueDAO dao;

    public FacadeMetierMusiqueImpl() {
            this.dao = DaosFactory.fabriquerMusiqueDAO();
    }

    @Override
    public Set<Musique> findAll() throws BusinessException {
        try {
            return StreamSupport.stream(this.dao.findAll().spliterator(), false).collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<Musique> findByCompositeur(Compositeur compositeur) throws BusinessException {
        try {
            return this.dao.findByCompositeur(compositeur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Musique musique) throws BusinessException {
        try {
            this.dao.delete(musique.getId());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Musique save(Musique musique) throws BusinessException {
        try {
            return this.dao.save(musique).orElseThrow();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
