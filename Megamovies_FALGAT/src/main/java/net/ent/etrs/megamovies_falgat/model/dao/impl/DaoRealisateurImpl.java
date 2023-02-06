package net.ent.etrs.megamovies_falgat.model.dao.impl;

import net.ent.etrs.megamovies_falgat.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class DaoRealisateurImpl extends AbstractJpaDao<Realisateur, Serializable> implements DaoRealisateur {


    @Override
    public List<Realisateur> readAllByGenre(Genre genre) throws DaoException {
        try {
            return this.em.createQuery("SELECT r FROM Realisateur r WHERE :genre member of r.genres", Realisateur.class)
                    .setParameter("genre", genre)
                    .getResultList();

        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
