package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.Film;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface FacadeMetierFilm extends BaseFacade<Film, Serializable> {
    void initialisationFilm() throws BusinessException;
    Set<Film> findByRealisateur(Realisateur realisateur) throws BusinessException;
    Set<Film> findByGenre(Genre genre) throws BusinessException;
    Set<Film> findByDateSortie(LocalDate dateSortie) throws BusinessException;
    Optional<Film> findByTitre(String titre) throws BusinessException;
}
