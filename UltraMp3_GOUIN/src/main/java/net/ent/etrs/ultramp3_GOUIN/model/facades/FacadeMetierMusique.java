package net.ent.etrs.ultramp3_GOUIN.model.facades;


import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;
import net.ent.etrs.ultramp3_GOUIN.model.facades.exceptions.BusinessException;

import java.util.Set;

public interface FacadeMetierMusique {
    Set<Musique> findAll() throws BusinessException;

    Set<Musique> findByCompositeur(final Compositeur compositeur) throws BusinessException;

    void delete(Musique musique) throws BusinessException;

    Musique save(Musique musique) throws BusinessException;
}
