package net.ent.etrs.Megamovies_SANTOS.model.model.daos.impl;

import net.ent.etrs.Megamovies_SANTOS.model.model.daos.RealisateurDao;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.DaoException;

import java.util.Objects;
import java.util.Set;

public class RealisateurDaoImpl extends AbstractJpaDao<Realisateur,Long> implements RealisateurDao {
    protected static RealisateurDaoImpl dao;
    public static RealisateurDao getInstance() {
        if (Objects.isNull(RealisateurDaoImpl.dao)) {
            RealisateurDaoImpl.dao = new RealisateurDaoImpl();
        }
        return RealisateurDaoImpl.dao;
    }

    @Override
    public Set<Realisateur> readAll() throws DaoException {
        try {
        return Set.of(

            this.em.createQuery("SELECT m FROM Realisateur m", Realisateur.class).getSingleResult());

        }catch (Exception e){
            throw new DaoException(e.getMessage(),e);
        }

             //   (Set<Realisateur>) this.em.createQuery("SELECT m FROM Realisateur m ",Realisateur.class)


    }

    @Override
    public Set<Realisateur> findByGenre(Genre genre) throws DaoException {
        try {
            return Set.of(
                    this.em.createQuery("SELECT m FROM Realisateur m WHERE m.genres = :genre", Realisateur.class)
                            .setParameter("genre", genre)
                            .getSingleResult()


            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
