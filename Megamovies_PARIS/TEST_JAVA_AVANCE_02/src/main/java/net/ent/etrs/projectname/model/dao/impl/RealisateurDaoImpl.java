package net.ent.etrs.projectname.model.dao.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projectname.model.dao.RealisateurDao;
import net.ent.etrs.projectname.model.dao.exception.DaoException;
import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.entities.reference.Genre;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.*;


public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur, Long> implements RealisateurDao {

    @Override
    public Set<Realisateur> findByGenre(Genre genre) {
        Set<Realisateur> realisateurSet = new HashSet<>();
        try {
            TypedQuery<Realisateur> query = super.getEm()
                    .createQuery(" SELECT r FROM Realisateur r WHERE  r.genres = :genre", Realisateur.class);
            query.setParameter("genre", genre);
            realisateurSet = (Set<Realisateur>) query.getResultList();
        } catch (NoResultException e) {
            realisateurSet = null;
        }
        return realisateurSet;
    }

    @Override
    public Set<Realisateur> readAll(){
        Set<Realisateur> realisateurSet = new HashSet<>();
        try {
            TypedQuery<Realisateur> query = super.getEm()
                    .createQuery(" SELECT r FROM Realisateur r", Realisateur.class);
        }catch (NoResultException e){
            realisateurSet = null;
        }
        return realisateurSet;
    }

}
