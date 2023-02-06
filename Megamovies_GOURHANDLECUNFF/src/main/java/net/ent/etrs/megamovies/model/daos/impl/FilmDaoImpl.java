package net.ent.etrs.megamovies.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmDaoImpl extends AbstractJpaDao<Film, Serializable> implements FilmDao {
    private static FilmDaoImpl dao;

    protected static  FilmDaoImpl getInstance() {
        if (Objects.isNull(FilmDaoImpl.dao)) {
            FilmDaoImpl.dao = new FilmDaoImpl();
        }
        return FilmDaoImpl.dao;
    }
    /**
     * @param realisateur
     * @return
     * @throws DaoException
     */
    @Override
    public Iterable<Film> findByRealisateur(Realisateur realisateur) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.realisateur = :realisateur", Film.class)
                            .setParameter("realisateur", realisateur)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * @param genre
     * @return
     * @throws DaoException
     */
    @Override
    public Iterable<Film> findByGenre(Genre genre) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.genre = :genre", Film.class)
                            .setParameter("genre", genre)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * @param dateSortie
     * @return
     * @throws DaoException
     */
    @Override
    public Iterable<Film> findByDateSortie(LocalDate dateSortie) throws DaoException {
        try {
            return new HashSet<>(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.dateSortie = :dateSortie", Film.class)
                            .setParameter("dateSortie", dateSortie)
                            .getResultList()
            );
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
    /**
     * @param titre
     * @return
     * @throws DaoException
     */
    @Override
    public Optional<Film> findByTitre(String titre) throws DaoException {
        try {
            return Optional.of(
                    this.em.createQuery("SELECT f FROM Film f WHERE f.titre = :titre", Film.class)
                            .setParameter("titre", titre)
                            .getSingleResult()
            );
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
