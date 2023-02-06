package net.ent.etrs.megamovies.model.dao.impl;

import net.ent.etrs.megamovies.model.dao.DaoFilm;
import net.ent.etrs.megamovies.model.dao.jpa.ReqDao;
import net.ent.etrs.megamovies.model.entities.Film;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Optional;

public class DaoFilmImpl extends AbstractDao<Film, Serializable> implements DaoFilm {

    @Override
    public Optional<Film> searchFilmByTitre(String titre) {

        try {
            TypedQuery<Film> tp = super.getEm()
                    .createQuery(ReqDao.RECHERCHER_FILM_PAR_TITRE, Film.class);
            tp.setParameter("titre", titre);
            return Optional.ofNullable(tp.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
