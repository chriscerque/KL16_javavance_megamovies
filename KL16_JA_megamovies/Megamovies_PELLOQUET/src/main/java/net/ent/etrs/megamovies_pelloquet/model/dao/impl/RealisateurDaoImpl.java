package net.ent.etrs.megamovies_pelloquet.model.dao.impl;




import net.ent.etrs.megamovies_pelloquet.model.dao.AbstractDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.RealisateurDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.references.ConstantesDao;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RealisateurDaoImpl extends AbstractDao<Realisateur, Serializable> implements RealisateurDao {
    @Override
    public Set<Realisateur> searchRealisateurByGenre(Genre genre) {
        List<Realisateur> lst = new ArrayList<>();
        try {
            TypedQuery<Realisateur> query = super.getEm().createQuery(ConstantesDao.QUERY_REALISATEUR_PAR_GENRE, Realisateur.class);
            query.setParameter("genre", genre);
            lst = query.getResultList();
        } catch (NoResultException e) {
            lst = null;
        }
        return lst.stream().collect(Collectors.toSet());
    }
}
