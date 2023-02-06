package net.ent.etrs.ultramp3_GOUIN.model.daos;

import net.ent.etrs.ultramp3_GOUIN.model.daos.exceptions.DaoException;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Compositeur;
import net.ent.etrs.ultramp3_GOUIN.model.entities.Musique;

import java.io.Serializable;
import java.util.Set;

public interface MusiqueDAO extends BaseDao<Musique, Serializable> {

    Set<Musique> findByCompositeur(Compositeur compositeur) throws DaoException;
}
